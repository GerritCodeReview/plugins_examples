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

import com.google.gerrit.extensions.common.PluginDefinedInfo;
import com.google.gerrit.server.DynamicOptions.BeanProvider;
import com.google.gerrit.server.change.ChangeAttributeFactory;
import com.google.gerrit.server.query.change.ChangeData;

public class AttributeFactory implements ChangeAttributeFactory {

  public class PluginAttribute extends PluginDefinedInfo {
    public String exampleName;
    public String changeValue;

    public PluginAttribute(ChangeData c) {
      this.exampleName = "Plugin Defined Attribute Example";
      this.changeValue = Integer.toString(c.getId().get());
    }
  }

  @Override
  public PluginDefinedInfo create(ChangeData c, BeanProvider bp, String plugin) {
    return new PluginAttribute(c);
  }
}
