// Copyright (C) 2017 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.changequeryattributes;

import com.google.gerrit.entities.Change;
import com.google.gerrit.extensions.common.PluginDefinedInfo;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.change.ChangePluginDefinedInfoFactory;
import com.google.gerrit.server.query.change.ChangeData;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.args4j.Option;

public class AttributeFactory implements ChangePluginDefinedInfoFactory {
  protected MyChangeOptions options;

  public class PluginAttribute extends PluginDefinedInfo {
    public String exampleName;
    public String changeValue;

    public PluginAttribute(ChangeData c) {
      this.exampleName = "Plugin Defined Attribute Example";
      this.changeValue = Integer.toString(c.getId().get());
    }
  }

  @Override
  public Map<Change.Id, PluginDefinedInfo> createPluginDefinedInfos(
      Collection<ChangeData> cds, DynamicOptions.BeanProvider bp, String plugin) {
    Map<Change.Id, PluginDefinedInfo> out = new HashMap<>();
    if (options == null) {
      options = (MyChangeOptions) bp.getDynamicBean(plugin);
    }
    if (options.all) {
      cds.forEach(cd -> out.put(cd.getId(), new PluginAttribute(cd)));
    }
    return out;
  }

  public class MyChangeOptions implements DynamicOptions.DynamicBean {
    @Option(name = "--all", usage = "Include plugin output")
    public boolean all = false;
  }
}
