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

package com.googlesource.gerrit.plugins.examples.patchseteventlistener;

import com.google.gerrit.extensions.api.changes.ReviewInput;
import com.google.gerrit.extensions.api.changes.ReviewInput.RobotCommentInput;
import com.google.gerrit.extensions.api.GerritApi;
import com.google.gerrit.extensions.restapi.RestApiException;
import com.google.gerrit.server.events.Event;
import com.google.gerrit.server.events.EventDispatcher;
import com.google.gerrit.server.events.EventListener;
import com.google.gerrit.server.events.PatchSetCreatedEvent;
import com.google.gerrit.server.plugincontext.PluginItemContext;

import com.google.common.collect.ImmutableList;
import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import java.util.Collections;

public class PatchSetEventListener implements EventListener {

  private final PluginItemContext<EventDispatcher> dispatcher;
  private final GerritApi gerritApi;
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  @Inject
  PatchSetEventListener(
      PluginItemContext<EventDispatcher> dispatcher,
      GerritApi gerritApi) {
    this.dispatcher = dispatcher;
    this.gerritApi = gerritApi;
  }

  @Override
  public void onEvent(Event receiveEvent) {
    if (! receiveEvent.getType().equals("patchset-created")){
      return;
    }
    PatchSetCreatedEvent event = (PatchSetCreatedEvent) receiveEvent;

    if (!event.change.get().commitMessage.startsWith("Example: ")) {
      RobotCommentInput robotCommentInput = new RobotCommentInput();
      robotCommentInput.robotId = "patchset-example";
      robotCommentInput.robotRunId = "1";
      robotCommentInput.line = 0;
      robotCommentInput.message = "Your commit message should start with Example";
      robotCommentInput.path = "/COMMIT_MSG";

      ReviewInput reviewInput = new ReviewInput();
      reviewInput.robotComments =
          Collections.singletonMap(robotCommentInput.path, ImmutableList.of(robotCommentInput));
      reviewInput.message = "Commit message validation";

      try {
        this.gerritApi.changes().id(event.change.get().id).current().review(reviewInput);
      } catch (RestApiException e) {
        logger.atWarning().log(e.getMessage());
      }
    }
  }
}
