package com.googlesource.gerrit.modules.cache.infinispan;

import com.google.common.flogger.FluentLogger;
import com.google.gerrit.common.Nullable;
import com.google.gerrit.lifecycle.LifecycleModule;
import com.google.gerrit.server.ModuleImpl;
import com.google.gerrit.server.cache.CacheModule;
import com.google.gerrit.server.cache.PersistentCacheFactory;
import com.google.gerrit.server.config.GerritServerConfig;
import com.google.gerrit.server.config.SitePaths;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.eclipse.jgit.lib.Config;

@ModuleImpl(name = CacheModule.PERSISTENT_MODULE)
public class InfinispanCacheModule extends LifecycleModule {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  @Override
  protected void configure() {
    bind(PersistentCacheFactory.class).to(InfinispanCacheFactory.class);
    listener().to(InfinispanCacheFactory.class);
  }

  @Provides
  @Singleton
  @Nullable
  @InfinispanDir
  Path getInfinispanDir(SitePaths site, @GerritServerConfig Config config) {
    String name = config.getString("cache", null, "directory");
    if (name == null) {
      return null;
    }
    Path loc = site.resolve(name);
    if (!Files.exists(loc)) {
      try {
        Files.createDirectories(loc);
      } catch (IOException e) {
        logger.atWarning().log("Can't create disk cache: %s", loc.toAbsolutePath());
        return null;
      }
    }
    if (!Files.isWritable(loc)) {
      logger.atWarning().log("Can't write to disk cache: %s", loc.toAbsolutePath());
      return null;
    }
    logger.atInfo().log("Enabling disk cache %s", loc.toAbsolutePath());
    return loc;
  }
}
