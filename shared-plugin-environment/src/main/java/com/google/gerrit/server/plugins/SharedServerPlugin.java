// Copyright (C) 2021 The Android Open Source Project
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

package com.google.gerrit.server.plugins;

import static com.google.gerrit.extensions.registration.PrivateInternals_DynamicTypes.dynamicMapsOf;

import com.google.common.collect.Lists;
import com.google.gerrit.common.Nullable;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.extensions.registration.PrivateInternals_DynamicTypes;
import com.google.gerrit.extensions.registration.RegistrationHandle;
import com.google.gerrit.server.PluginUser;
import com.google.gerrit.server.config.GerritRuntime;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.eclipse.jgit.internal.storage.file.FileSnapshot;

public class SharedServerPlugin extends ServerPlugin {
  private final ClassLoader classLoader;
  private final SharedPluginEnv sharedPluginEnv;
  private Injector baseInjector = null;
  private Map<TypeLiteral<?>, DynamicMap<?>> sysMaps = Collections.emptyMap();

  public SharedServerPlugin(
      String name,
      String pluginCanonicalWebUrl,
      PluginUser pluginUser,
      Path srcJar,
      FileSnapshot snapshot,
      PluginContentScanner scanner,
      Path dataDir,
      ClassLoader classLoader,
      String metricsPrefix,
      GerritRuntime gerritRuntime,
      SharedPluginEnv sharedPluginEnv)
      throws InvalidPluginException {
    super(
        name,
        pluginCanonicalWebUrl,
        pluginUser,
        srcJar,
        snapshot,
        scanner,
        dataDir,
        classLoader,
        metricsPrefix,
        gerritRuntime);

    this.classLoader = classLoader;
    this.sharedPluginEnv = sharedPluginEnv;
  }

  ClassLoader getClassLoader() {
    return classLoader;
  }

  @Override
  public String getVersion() {
    return super.getVersion() + "-shared";
  }

  @Override
  protected Injector newRootInjector(PluginGuiceEnvironment env) {

    List<Module> modules = Lists.newArrayListWithCapacity(2);
    if (getApiType() == ApiType.PLUGIN) {
      baseInjector = sharedPluginEnv.getRootInjector(env);
      sysMaps = dynamicMapsOf(baseInjector);
    }
    modules.add(new ServerPluginInfoModule(this, env.getServerMetrics()));

    if (baseInjector == null) {
      return Guice.createInjector(modules);
    }
    return baseInjector.createChildInjector(modules);
  }

  @Override
  protected void start(PluginGuiceEnvironment env) throws Exception {
    super.start(env);

    attachMap(sysMaps, getSysInjector(), this);
  }

  private void attachMap(
      Map<TypeLiteral<?>, DynamicMap<?>> maps, @Nullable Injector src, Plugin plugin) {
    for (RegistrationHandle h :
        PrivateInternals_DynamicTypes.attachMaps(src, plugin.getName(), maps)) {
      plugin.add(h);
    }
  }
}
