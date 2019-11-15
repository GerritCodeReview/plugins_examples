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

package com.googlesource.gerrit.plugins.examples.restapipostproject;

import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiAction;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.project.ProjectResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

class HelloProjectAction
    implements UiAction<ProjectResource>,
        RestModifyView<ProjectResource, HelloProjectAction.Input> {

  private Provider<CurrentUser> user;

  static class Input {
    boolean french;
    String message;
  }

  @Inject
  HelloProjectAction(Provider<CurrentUser> user) {
    this.user = user;
  }

  private boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }

  private Object firstNonNull(String first, String second) {
    if (first != null) {
      return first;
    }
    if (second != null) {
      return second;
    }
    throw new NullPointerException();
  }

  @Override
  public String apply(ProjectResource rsrc, Input input) {
    final String greeting = input.french ? "Bonjour" : "Hello";
    return String.format(
        "%s %s from project %s!",
        greeting,
        isNullOrEmpty(input.message)
            ? firstNonNull(user.get().getUserName().get(), "world")
            : input.message,
        rsrc.getName());
  }

  @Override
  public Description getDescription(ProjectResource resource) {
    return new Description()
        .setLabel("Say hello")
        .setTitle("Say hello in different languages")
        .setVisible(user.get() instanceof IdentifiedUser);
  }
}
