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

import com.google.gerrit.entities.BranchNameKey;
import com.google.gerrit.entities.Change;
import com.google.gerrit.entities.PatchSet;
import com.google.gerrit.extensions.common.PluginDefinedInfo;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.change.ChangePluginDefinedInfoFactory;
import com.google.gerrit.server.query.change.ChangeData;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PdAttributeFactory implements ChangePluginDefinedInfoFactory {
  private static final Logger log = LoggerFactory.getLogger(PdAttributeFactory.class);

  protected DynamicMap<Object> pluginProvidedApis;
  protected Modules.MyQueryOptions options;
  protected Object dependencyResolver;

  @Inject
  public PdAttributeFactory(DynamicMap<Object> pluginProvidedApis) {
    this.pluginProvidedApis = pluginProvidedApis;
  }

  @Override
  public Map<Change.Id, PluginDefinedInfo> createPluginDefinedInfos(
      Collection<ChangeData> cds, DynamicOptions.BeanProvider beanProvider, String plugin) {
    if (options == null) {
      options = (Modules.MyQueryOptions) beanProvider.getDynamicBean(plugin);
    }
    dependencyResolver = pluginProvidedApis.get(Modules.DEPENDS_ON_PLUGIN, "DependencyResolver");
    if (dependencyResolver == null) {
      log.warn("No dependency resolver has been found for plugin {}", Modules.DEPENDS_ON_PLUGIN);
      return Collections.emptyMap();
    }

    Map<Change.Id, PluginDefinedInfo> pluginInfosByChange = new HashMap<>();
    for (ChangeData c : cds) {
      PluginDefinedInfo pdinfo = create(c);
      if (pdinfo != null) {
        pluginInfosByChange.put(c.getId(), pdinfo);
      }
    }
    return pluginInfosByChange;
  }

  protected PluginDefinedInfo create(ChangeData c) {
    if (options != null && options.resolveDependsOn) {
      PluginDefinedInfo pluginDefinedInfo = new PluginDefinedInfo();
      pluginDefinedInfo.message = "Ran dependency resolver";
      Set<Set<BranchNameKey>> deliverables = new HashSet<>();

      try {
        Method resolveDeps =
            dependencyResolver
                .getClass()
                .getMethod("resolveDependencies", PatchSet.Id.class, Set.class);
        resolveDeps.invoke(dependencyResolver, c.change().currentPatchSetId(), deliverables);
      } catch (Exception e) {
        pluginDefinedInfo.message = "Error while running dependency resolver";
        log.error("Exception", e);
      }
      return pluginDefinedInfo;
    }
    return null;
  }
}
