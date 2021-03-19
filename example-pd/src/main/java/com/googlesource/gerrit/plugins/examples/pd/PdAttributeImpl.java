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
import com.google.gerrit.extensions.common.PluginDefinedInfo;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.extensions.registration.PluginProvidedApi;
import com.google.gerrit.server.project.InvalidChangeOperationException;
import com.google.gerrit.server.query.change.ChangeData;
import com.google.inject.Inject;
import com.googlesource.gerrit.plugins.examples.dependson.extensions.DependencyResolver;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdAttributeImpl implements PdAttribute {
  private static final Logger log = LoggerFactory.getLogger(PdAttributeImpl.class);

  protected DynamicMap<PluginProvidedApi> pluginProvidedApis;
  protected DependencyResolver dependencyResolver;

  @Inject
  public PdAttributeImpl(DynamicMap<PluginProvidedApi> pluginProvidedApis) {
    this.pluginProvidedApis = pluginProvidedApis;
  }

  @Override
  public Map<Change.Id, PluginDefinedInfo> createPluginDefinedInfos(
      Collection<ChangeData> cds, Modules.MyQueryOptions options) {
    dependencyResolver =
        (DependencyResolver)
            pluginProvidedApis.get(Modules.DEPENDS_ON_PLUGIN, "DependencyResolver");

    Map<Change.Id, PluginDefinedInfo> pluginInfosByChange = new HashMap<>();
    for (ChangeData c : cds) {
      PluginDefinedInfo pdinfo = create(c, options);
      if (pdinfo != null) {
        pluginInfosByChange.put(c.getId(), pdinfo);
      }
    }
    return pluginInfosByChange;
  }

  protected PluginDefinedInfo create(ChangeData c, Modules.MyQueryOptions options) {
    if (options != null && options.resolveDependsOn) {
      PluginDefinedInfo pluginDefinedInfo = new PluginDefinedInfo();
      pluginDefinedInfo.message = "Ran dependency resolver";
      Set<Set<BranchNameKey>> deliverables = new HashSet<>();

      try {
        dependencyResolver.resolveDependencies(c.change().currentPatchSetId(), deliverables);
      } catch (InvalidChangeOperationException e) {
        pluginDefinedInfo.message = "Error while running dependency resolver";
        log.error("Exception", e);
      }
      return pluginDefinedInfo;
    }
    return null;
  }
}
