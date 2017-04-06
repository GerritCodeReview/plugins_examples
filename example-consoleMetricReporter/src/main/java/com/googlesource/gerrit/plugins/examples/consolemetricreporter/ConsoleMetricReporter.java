// Copyright (C) 2015 The Android Open Source Project
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
package com.googlesource.gerrit.plugins.examples.consolemetricreporter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.extensions.events.LifecycleListener;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.concurrent.TimeUnit;
import org.eclipse.jgit.lib.Config;

/**
 * Demonstration of how to add a new Dropwizard Metrics Reporter using Gerrit's plug-in API.
 *
 * @see <a
 *     href="https://dropwizard.github.io/metrics/3.1.0/getting-started/#reporting-via-jmx">here</a>
 * @see <a
 *     href="https://dropwizard.github.io/metrics/3.1.0/getting-started/#reporting-via-http">here</a>
 * @see <a
 *     href="https://dropwizard.github.io/metrics/3.1.0/getting-started/#other-reporting">here</a>
 *     <p>Also shows how to fetch plug-in specific configuration values. <@see
 *     com.google.gerrit.server.config.PluginConfigFactory>
 *     <p>Enable by adding the file etc/example-consoleMetricReporter.config:
 *     <pre>
 *  [console-metrics]
 *      enabled = true
 * </pre>
 */
@Listen
@Singleton
public class ConsoleMetricReporter implements LifecycleListener {
  private ConsoleReporter consoleReporter;
  private boolean enabled;

  @Inject
  public ConsoleMetricReporter(
      MetricRegistry registry, PluginConfigFactory configFactory, @PluginName String pluginName) {
    Config config = configFactory.getGlobalPluginConfig(pluginName);
    enabled = config.getBoolean("console-metrics", "enabled", false);
    if (!enabled) {
      return;
    }
    this.consoleReporter =
        ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
  }

  @Override
  public void start() {
    if (enabled) {
      consoleReporter.start(1, TimeUnit.MINUTES);
    }
  }

  @Override
  public void stop() {
    if (enabled) {
      consoleReporter.stop();
    }
  }
}
