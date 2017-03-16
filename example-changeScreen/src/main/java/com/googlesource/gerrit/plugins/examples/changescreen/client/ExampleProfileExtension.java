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

package com.googlesource.gerrit.plugins.examples.changescreen.client;

import com.google.gerrit.client.GerritUiExtensionPoint;
import com.google.gerrit.client.info.AccountInfo;
import com.google.gerrit.plugin.client.extension.Panel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.VerticalPanel;

/** Extension for the user profile screen. */
public class ExampleProfileExtension extends VerticalPanel {
  static class Factory implements Panel.EntryPoint {
    @Override
    public void onLoad(Panel panel) {
      AccountInfo accountInfo = panel.getObject(GerritUiExtensionPoint.Key.ACCOUNT_INFO).cast();
      panel.setWidget(new ExampleProfileExtension(accountInfo));
    }
  }

  ExampleProfileExtension(AccountInfo accountInfo) {
    Grid g = new Grid(3, 2);
    g.addStyleName("infoBlock");
    g.addStyleName("accountInfoBlock");
    CellFormatter fmt = g.getCellFormatter();

    // TODO: fetch employer and department via REST from server,
    // e.g. GET /accounts/self/example~info

    g.setText(0, 0, "Employer");
    fmt.addStyleName(0, 0, "header");
    g.setText(0, 1, "Example Corporation");

    g.setText(1, 0, "Department");
    fmt.addStyleName(1, 0, "header");
    g.setText(1, 1, "Cookies " + accountInfo.email());

    g.setText(2, 0, "Example Email");
    fmt.addStyleName(2, 0, "header");
    g.setText(
        2, 1, accountInfo.username() != null ? accountInfo.username() + "@example.com" : "N/A");
    add(g);

    fmt.addStyleName(0, 0, "topmost");
    fmt.addStyleName(0, 1, "topmost");
    fmt.addStyleName(2, 0, "bottomheader");
  }
}
