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

package com.googlesource.gerrit.plugins.examples.pd;

import com.google.gerrit.entities.Change;
import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.extensions.common.PluginDefinedInfo;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.change.ChangePluginDefinedInfoFactory;
import com.google.gerrit.server.query.change.ChangeData;
import com.google.gerrit.sshd.commands.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.googlesource.gerrit.plugins.sharedenvironment.SharedPluginEnv;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Modules {
  private static final Logger log = LoggerFactory.getLogger(Modules.class);
  protected static final String DEPENDS_ON_PLUGIN = "example-depends-on";
  protected static final String PD_PLUGIN = "example-pd";

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(DynamicOptions.DynamicBean.class)
          .annotatedWith(Exports.named(Query.class))
          .to(MyQueryOptions.class);

      bind(ChangePluginDefinedInfoFactory.class)
          .annotatedWith(Exports.named(PD_PLUGIN))
          .to(PdFactory.class);
    }
  }

  public static class PdFactory implements ChangePluginDefinedInfoFactory {
    protected final SharedPluginEnv sharedPluginEnv;

    @Inject
    public PdFactory(SharedPluginEnv sharedPluginEnv) {
      this.sharedPluginEnv = sharedPluginEnv;
    }

    @Override
    public Map<Change.Id, PluginDefinedInfo> createPluginDefinedInfos(
        Collection<ChangeData> cds, DynamicOptions.BeanProvider beanProvider, String plugin) {
      try {
        System.out.println(
            "Class "
                + PdAttributeFactory.class.getName()
                + " loaded into "
                + System.identityHashCode(PdAttributeFactory.class));
        ChangePluginDefinedInfoFactory attributeFactory =
            (ChangePluginDefinedInfoFactory)
                sharedPluginEnv
                    .setTargetPluginName(PD_PLUGIN)
                    .addDependentPlugin(DEPENDS_ON_PLUGIN)
                    .instantiate("com.googlesource.gerrit.plugins.examples.pd.PdAttributeFactory");
        System.out.println(
            "Class "
                + attributeFactory.getClass()
                + " if instance is "
                + System.identityHashCode(attributeFactory.getClass()));

        return attributeFactory.createPluginDefinedInfos(cds, beanProvider, plugin);
      } catch (ClassNotFoundException | SharedPluginEnv.NoSuchPluginException e) {
        log.error("Exception", e);
      }
      return new HashMap<>();
    }
  }

  public static class MyQueryOptions implements DynamicOptions.DynamicBean {
    @Option(name = "--resolve-depends-on", usage = "Resolve any unresolved")
    public boolean resolveDependsOn = false;
  }
}
