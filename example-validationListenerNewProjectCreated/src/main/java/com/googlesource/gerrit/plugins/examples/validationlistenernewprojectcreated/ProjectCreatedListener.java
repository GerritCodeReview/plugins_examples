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

package com.googlesource.gerrit.plugins.examples.validationlistenernewprojectcreated;

import com.google.gerrit.extensions.api.GerritApi;
import com.google.gerrit.extensions.api.projects.ProjectApi;
import com.google.gerrit.extensions.events.NewProjectCreatedListener;
import com.google.gerrit.extensions.restapi.RestApiException;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectCreatedListener implements NewProjectCreatedListener {
  private static final Logger log = LoggerFactory.getLogger(ProjectCreatedListener.class);

  @Inject protected GerritApi gApi;

  @Override
  public void onNewProjectCreated(Event event) {
    String name = event.getProjectName();
    try {
      ProjectApi api = gApi.projects().name(name);
      log.info(String.format("New project: '%s', Parent: '%s'", name, api.get().parent));
    } catch (RestApiException e) {
      log.error(String.format("Failed to get info for new project %s", name), e);
    }
  }
}
