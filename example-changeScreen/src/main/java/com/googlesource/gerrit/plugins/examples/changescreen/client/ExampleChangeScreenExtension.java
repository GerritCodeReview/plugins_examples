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
import com.google.gerrit.client.info.ChangeInfo;
import com.google.gerrit.client.info.ChangeInfo.RevisionInfo;
import com.google.gerrit.plugin.client.extension.Panel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwtexpui.clippy.client.CopyableLabel;

/** Extension for change screen that displays the numeric change ID with copy-to-clipboard icon. */
public class ExampleChangeScreenExtension extends VerticalPanel {
  static class Factory implements Panel.EntryPoint {
    @Override
    public void onLoad(Panel panel) {
      panel.setWidget(new ExampleChangeScreenExtension(panel));
    }
  }

  ExampleChangeScreenExtension(Panel panel) {
    ChangeInfo change = panel.getObject(GerritUiExtensionPoint.Key.CHANGE_INFO).cast();
    RevisionInfo rev = panel.getObject(GerritUiExtensionPoint.Key.REVISION_INFO).cast();

    Grid g = new Grid(2, 2);
    g.addStyleName("infoBlock");
    CellFormatter fmt = g.getCellFormatter();

    g.setText(0, 0, "Numeric Change ID");
    fmt.addStyleName(0, 0, "header");
    fmt.addStyleName(0, 0, "topmost");
    fmt.addStyleName(0, 1, "topmost");
    g.setWidget(0, 1, new CopyableLabel(Integer.toString(change._number())));
    add(g);

    g.setText(1, 0, "Patch Set ID");
    fmt.addStyleName(1, 0, "header");
    fmt.addStyleName(1, 0, "bottomheader");
    fmt.addStyleName(1, 1, "bottomheader");
    g.setWidget(1, 1, new CopyableLabel(String.valueOf(rev._number())));
    add(g);
  }
}
