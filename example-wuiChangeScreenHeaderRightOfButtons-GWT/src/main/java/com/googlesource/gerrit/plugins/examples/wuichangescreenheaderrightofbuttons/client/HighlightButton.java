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

package com.googlesource.gerrit.plugins.examples.wuichangescreenheaderrightofbuttons.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwtexpui.safehtml.client.SafeHtmlBuilder;

/**
 * Highlight button for header line in change screen.
 *
 * <p>This class can *only* be used within a panel that extends the header line of the change
 * screen, but will not work standalone.
 */
public class HighlightButton extends Button {

  public HighlightButton(String text) {
    // Create Button with inner div. This is required to get proper styling
    // in the context of the change screen.
    super(
        (new SafeHtmlBuilder())
            .openDiv()
            .appendAttribute("style", "color: #fff;")
            .append(text)
            .closeDiv());
    getElement().removeClassName("gwt-Button");
    getElement().getStyle().setBackgroundColor("#4d90fe");
  }
}
