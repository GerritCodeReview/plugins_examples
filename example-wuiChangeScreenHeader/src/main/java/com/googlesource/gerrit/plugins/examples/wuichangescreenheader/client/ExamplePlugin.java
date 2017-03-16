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

package com.googlesource.gerrit.plugins.examples.wuichangescreenheader.client;

import com.google.gerrit.client.GerritUiExtensionPoint;
import com.google.gerrit.client.Resources;
import com.google.gerrit.plugin.client.Plugin;
import com.google.gerrit.plugin.client.PluginEntryPoint;
import com.google.gerrit.plugin.client.extension.Panel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;

public class ExamplePlugin extends PluginEntryPoint {
  public static final Resources RESOURCES = GWT.create(Resources.class);

  @Override
  public void onPluginLoad() {
    Plugin.get()
        .panel(
            GerritUiExtensionPoint.CHANGE_SCREEN_HEADER, new ChangeScreenStatusExtension.Factory());
  }
}
