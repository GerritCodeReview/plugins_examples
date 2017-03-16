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

import com.google.gerrit.plugin.client.extension.Panel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;

/** Extension for change screen that displays a status in the header bar. */
public class BuildsDropDownPanel extends FlowPanel {
  static class Factory implements Panel.EntryPoint {
    @Override
    public void onLoad(Panel panel) {
      panel.setWidget(new BuildsDropDownPanel());
    }
  }

  BuildsDropDownPanel() {
    Grid g = new Grid(3, 4);
    g.addStyleName("infoBlock");
    CellFormatter fmt = g.getCellFormatter();

    g.setText(0, 0, "State");
    fmt.addStyleName(0, 0, "header");
    g.setText(0, 1, "PS");
    fmt.addStyleName(0, 1, "header");
    g.setText(0, 2, "Date");
    fmt.addStyleName(0, 2, "header");
    g.setText(0, 3, "Log");
    fmt.addStyleName(0, 3, "header");

    HorizontalPanel p = new HorizontalPanel();
    p.add(new Image(ExamplePlugin.RESOURCES.greenCheck()));
    p.add(new InlineLabel("OK"));
    g.setWidget(1, 0, p);
    g.setWidget(1, 1, new InlineLabel("2"));
    g.setWidget(1, 2, new InlineLabel("2015-07-09 11:06:13"));
    g.setWidget(1, 3, new InlineHyperlink("Build Log", "TODO"));

    p = new HorizontalPanel();
    p.add(new Image(ExamplePlugin.RESOURCES.redNot()));
    p.add(new InlineLabel("FAILED"));
    g.setWidget(2, 0, p);
    g.setWidget(2, 1, new InlineLabel("1"));
    g.setWidget(2, 2, new InlineLabel("2015-07-09 09:17:28"));
    g.setWidget(2, 3, new InlineHyperlink("Build Log", "TODO"));

    fmt.addStyleName(0, 0, "topmost");
    fmt.addStyleName(0, 1, "topmost");
    fmt.addStyleName(0, 2, "topmost");
    fmt.addStyleName(0, 3, "topmost");

    add(new PopDownButton("Builds", g));
  }
}
