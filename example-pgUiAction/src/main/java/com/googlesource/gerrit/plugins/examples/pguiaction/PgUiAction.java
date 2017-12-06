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

package com.googlesource.gerrit.plugins.examples.pguiaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gerrit.extensions.restapi.AuthException;
import com.google.gerrit.extensions.restapi.BadRequestException;
import com.google.gerrit.extensions.restapi.ResourceConflictException;
import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiAction;
import com.google.gerrit.server.change.ChangeResource;

public class PgUiAction
    implements UiAction<ChangeResource>, RestModifyView<ChangeResource, PgUiAction.Input> {
  private static final Logger LOG = LoggerFactory.getLogger(PgUiAction.class);
  private static String BUTTON_NAME = "Example btn";

  public static class Input {}

  @Override
  public com.google.gerrit.extensions.webui.UiAction.Description getDescription(
      ChangeResource resource) {
    return new UiAction.Description()
        .setLabel(BUTTON_NAME)
        .setTitle("This is an example button")
        .setVisible(true)
        .setEnabled(true);
  }

  @Override
  public Object apply(ChangeResource resource, Input input)
      throws AuthException, BadRequestException, ResourceConflictException, Exception {
    LOG.info(BUTTON_NAME + " was pressed!");
    return null;
  }
}
