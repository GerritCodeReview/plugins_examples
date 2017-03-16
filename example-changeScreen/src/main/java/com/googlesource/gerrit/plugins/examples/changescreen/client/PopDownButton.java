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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtexpui.globalkey.client.GlobalKey;
import com.google.gwtexpui.safehtml.client.SafeHtmlBuilder;

/**
 * Pop down button for header line in change screen.
 *
 * <p>This class implements a button that on click opens a pop down panel with the provided widget,
 * similar to the "Patch Sets", "Download" or "Included In" pop down panels on the change screen.
 *
 * <p>This class can *only* be used within a panel that extends the header line of the change
 * screen, but will not work standalone.
 */
public class PopDownButton extends Button {
  private final Widget widget;
  private PopupPanel popup;

  public PopDownButton(String text, Widget widget) {
    // Create Button with inner div. This is required to get proper styling
    // in the context of the change screen.
    super((new SafeHtmlBuilder()).openDiv().append(text).closeDiv());
    getElement().removeClassName("gwt-Button");
    addClickHandler(
        new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            show();
          }
        });
    this.widget = widget;
  }

  private void show() {
    if (popup != null) {
      getElement().getStyle().clearFontWeight();
      popup.hide();
      return;
    }

    final Widget relativeTo = getParent();
    final PopupPanel p =
        new PopupPanel(true) {
          @Override
          public void setPopupPosition(int left, int top) {
            top -= Document.get().getBodyOffsetTop();

            int w = Window.getScrollLeft() + Window.getClientWidth();
            int r = relativeTo.getAbsoluteLeft() + relativeTo.getOffsetWidth();
            int right = w - r;
            Style style = getElement().getStyle();
            style.clearProperty("left");
            style.setPropertyPx("right", right);
            style.setPropertyPx("top", top);
          }
        };
    Style popupStyle = p.getElement().getStyle();
    popupStyle.setBorderWidth(0, Unit.PX);
    popupStyle.setBackgroundColor("#EEEEEE");
    p.addAutoHidePartner(getElement());
    p.addCloseHandler(
        new CloseHandler<PopupPanel>() {
          @Override
          public void onClose(CloseEvent<PopupPanel> event) {
            if (popup == p) {
              getElement().getStyle().clearFontWeight();
              popup = null;
            }
          }
        });
    p.add(widget);
    p.showRelativeTo(relativeTo);
    GlobalKey.dialog(p);
    getElement().getStyle().setFontWeight(FontWeight.BOLD);
    popup = p;
  }
}
