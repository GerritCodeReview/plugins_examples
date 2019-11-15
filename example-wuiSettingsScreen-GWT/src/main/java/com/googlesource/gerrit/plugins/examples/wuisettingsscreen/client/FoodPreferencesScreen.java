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

package com.googlesource.gerrit.plugins.examples.wuisettingsscreen.client;

import com.google.gerrit.plugin.client.screen.Screen;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

class FoodPreferencesScreen extends VerticalPanel {
  static class Factory implements Screen.EntryPoint {
    @Override
    public void onLoad(Screen screen) {
      screen.setPageTitle("Settings");
      screen.show(new FoodPreferencesScreen());
    }
  }

  FoodPreferencesScreen() {
    setStyleName("example-panel");

    Panel messagePanel = new VerticalPanel();
    messagePanel.add(new Label("Food Allergies or Dietary Concerns:"));
    TextArea txt = new TextArea();
    txt.addKeyPressHandler(
        new KeyPressHandler() {
          @Override
          public void onKeyPress(final KeyPressEvent event) {
            event.stopPropagation();
          }
        });
    txt.setVisibleLines(12);
    txt.setCharacterWidth(80);
    txt.getElement().setPropertyBoolean("spellcheck", false);
    messagePanel.add(txt);
    add(messagePanel);

    Button helloButton = new Button("Save");
    helloButton.addStyleName("example-helloButton");
    helloButton.addClickHandler(
        new ClickHandler() {
          @Override
          public void onClick(final ClickEvent event) {
            Window.alert("TODO: implement save");
          }
        });
    add(helloButton);
    helloButton.setEnabled(true);
  }
}
