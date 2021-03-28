// Copyright (C) 2014 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.sharedenvironment;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import com.google.common.flogger.FluentLogger;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.server.config.GerritRuntime;
import com.google.gerrit.server.config.PluginConfig;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.gerrit.server.config.SitePaths;
import com.google.gerrit.server.plugins.InvalidPluginException;
import com.google.gerrit.server.plugins.JarScanner;
import com.google.gerrit.server.plugins.PluginUtil;
import com.google.gerrit.server.plugins.ServerPlugin;
import com.google.gerrit.server.plugins.ServerPluginProvider;
import com.google.gerrit.server.plugins.SharedPluginEnv;
import com.google.gerrit.server.plugins.SharedServerPlugin;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import org.eclipse.jgit.internal.storage.file.FileSnapshot;

@Listen
@Singleton
public class SharedPluginProvider implements ServerPluginProvider {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private static final Set<String> SHARED_PLUGIN_EXTENSIONS = Sets.newHashSet("sar");
  static final String PLUGIN_TMP_PREFIX = "plugin_";

  private final String pluginProviderName;
  private final SitePaths sitePaths;
  private final PluginConfigFactory configFactory;
  private final Map<String, SharedServerPlugin> sharedPlugins;
  private final Provider<SharedPluginEnv> sharedPluginEnv;

  @Inject
  public SharedPluginProvider(
      SitePaths sitePaths,
      PluginConfigFactory configFactory,
      @PluginName String pluginProviderName,
      Provider<SharedPluginEnv> sharedPluginEnv) {
    this.sitePaths = sitePaths;
    this.configFactory = configFactory;
    this.pluginProviderName = pluginProviderName;
    this.sharedPlugins = new HashMap<>();
    this.sharedPluginEnv = sharedPluginEnv;
  }

  @Override
  public boolean handles(Path srcFile) {
    String scriptExtension = Files.getFileExtension(srcFile.toString()).toLowerCase();
    return SHARED_PLUGIN_EXTENSIONS.contains(scriptExtension);
  }

  @Override
  public String getPluginName(Path srcPath) {
    try {
      return MoreObjects.firstNonNull(getJarPluginName(srcPath), PluginUtil.nameOf(srcPath));
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Invalid plugin file " + srcPath + ": cannot get plugin name", e);
    }
  }

  public static String getJarPluginName(Path srcPath) throws IOException {
    try (JarFile jarFile = new JarFile(srcPath.toFile())) {
      return jarFile.getManifest().getMainAttributes().getValue("Gerrit-PluginName");
    }
  }

  @Override
  public String getProviderPluginName() {
    return pluginProviderName;
  }

  @Override
  public ServerPlugin get(Path srcPath, FileSnapshot snapshot, PluginDescription description)
      throws InvalidPluginException {
    try {
      String name = getPluginName(srcPath);
      String extension = "sar";
      try (InputStream in = java.nio.file.Files.newInputStream(srcPath)) {
        Path tmp = asTemp(in, tempNameFor(name), extension, sitePaths.tmp_dir);
        return loadJarPlugin(name, srcPath, snapshot, tmp, description);
      }
    } catch (IOException e) {
      throw new InvalidPluginException("Cannot load Jar plugin " + srcPath, e);
    }
  }

  static Path asTemp(InputStream in, String prefix, String suffix, Path dir) throws IOException {
    if (!java.nio.file.Files.exists(dir)) {
      java.nio.file.Files.createDirectories(dir);
    }
    Path tmp = java.nio.file.Files.createTempFile(dir, prefix, suffix);
    boolean keep = false;
    try (OutputStream out = java.nio.file.Files.newOutputStream(tmp)) {
      ByteStreams.copy(in, out);
      keep = true;
      return tmp;
    } finally {
      if (!keep) {
        java.nio.file.Files.delete(tmp);
      }
    }
  }

  protected ServerPlugin loadJarPlugin(
      String name, Path srcJar, FileSnapshot snapshot, Path tmp, PluginDescription description)
      throws IOException, InvalidPluginException, MalformedURLException {
    JarFile jarFile = new JarFile(tmp.toFile());
    boolean keep = false;
    try {

      List<URL> urls = new ArrayList<>(2);
      String overlay = System.getProperty("gerrit.plugin-classes");
      if (overlay != null) {
        Path classes = Paths.get(overlay).resolve(name).resolve("main");
        if (java.nio.file.Files.isDirectory(classes)) {
          logger.atInfo().log("plugin %s: including %s", name, classes);
          urls.add(classes.toUri().toURL());
        }
      }
      urls.add(tmp.toUri().toURL());

      ClassLoader pluginLoader =
          URLClassLoader.newInstance(
              urls.toArray(new URL[urls.size()]), getClass().getClassLoader());

      JarScanner jarScanner = createJarScanner(tmp);
      PluginConfig pluginConfig = configFactory.getFromGerritConfig(name);

      SharedServerPlugin plugin =
          new SharedServerPlugin(
              name,
              description.canonicalUrl,
              description.user,
              srcJar,
              snapshot,
              jarScanner,
              description.dataDir,
              pluginLoader,
              pluginConfig.getString("metricsPrefix", null),
              GerritRuntime.DAEMON,
              sharedPluginEnv.get());
      sharedPlugins.put(name, plugin);
      keep = true;
      return plugin;
    } finally {
      if (!keep) {
        jarFile.close();
      }
    }
  }

  private JarScanner createJarScanner(Path srcJar) throws InvalidPluginException {
    try {
      return new JarScanner(srcJar);
    } catch (IOException e) {
      throw new InvalidPluginException("Cannot scan plugin file " + srcJar, e);
    }
  }

  static String tempNameFor(String name) {
    SimpleDateFormat fmt = new SimpleDateFormat("yyMMdd_HHmm");
    return PLUGIN_TMP_PREFIX + name + "_" + fmt.format(new Date()) + "_";
  }

  public SharedServerPlugin getPlugin(String pluginName) {
    return sharedPlugins.get(pluginName);
  }
}
