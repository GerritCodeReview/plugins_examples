// Copyright (C) 2013 The Android Open Source Project
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

import com.google.gerrit.plugin.client.screen.Screen;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

class IndexScreen extends VerticalPanel {
  static class Factory implements Screen.EntryPoint {
    @Override
    public void onLoad(Screen screen) {
      screen.setPageTitle("cookbook index");
      screen.show(new IndexScreen());
    }
  }

  private TextBox usernameTxt;
  private TextArea greetingTxt;

  IndexScreen() {
    setStyleName("cookbook-panel");

    Panel labelImagePanel = new HorizontalPanel();
    Panel usernamePanel = new VerticalPanel();
    Image img = new Image(ExamplePlugin.RESOURCES.info());
    img.setTitle("User to send greetings to");
    labelImagePanel.add(new Label("Username"));
    labelImagePanel.add(img);
    labelImagePanel.add(new Label(":"));
    usernamePanel.add(labelImagePanel);
    usernameTxt =
        new TextBox() {
          @Override
          public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);
            if (event.getTypeInt() == Event.ONPASTE) {
              Scheduler.get()
                  .scheduleDeferred(
                      new ScheduledCommand() {
                        @Override
                        public void execute() {
                          if (getValue().trim().length() != 0) {
                            setEnabled(true);
                          }
                        }
                      });
            }
          }
        };
    usernameTxt.addKeyPressHandler(
        new KeyPressHandler() {
          @Override
          public void onKeyPress(final KeyPressEvent event) {
            event.stopPropagation();
          }
        });
    usernameTxt.sinkEvents(Event.ONPASTE);
    usernameTxt.setVisibleLength(40);
    usernamePanel.add(usernameTxt);
    add(usernamePanel);

    Panel messagePanel = new VerticalPanel();
    messagePanel.add(new Label("Message:"));
    greetingTxt = new TextArea();
    greetingTxt.addKeyPressHandler(
        new KeyPressHandler() {
          @Override
          public void onKeyPress(final KeyPressEvent event) {
            event.stopPropagation();
          }
        });
    greetingTxt.setVisibleLines(12);
    greetingTxt.setCharacterWidth(80);
    greetingTxt.getElement().setPropertyBoolean("spellcheck", false);
    messagePanel.add(greetingTxt);
    add(messagePanel);

    Button helloButton = new Button("Say Hello");
    helloButton.addStyleName("cookbook-helloButton");
    helloButton.addClickHandler(
        new ClickHandler() {
          @Override
          public void onClick(final ClickEvent event) {
            sayHello();
          }
        });
    add(helloButton);
    helloButton.setEnabled(true);
  }

  private void sayHello() {
    String username = usernameTxt.getValue();
    String greeting = greetingTxt.getText();
    if (username == null) {
      username = "";
    } else {
      username = username.trim();
    }
    if (greeting == null) {
      greeting = "";
    } else {
      greeting = greeting.trim();
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Hey ");
    sb.append(username.isEmpty() ? "Dude" : username);
    sb.append(", ");
    sb.append(greeting.isEmpty() ? "what's up?" : greeting);
    Window.alert(sb.toString());
  }
}
