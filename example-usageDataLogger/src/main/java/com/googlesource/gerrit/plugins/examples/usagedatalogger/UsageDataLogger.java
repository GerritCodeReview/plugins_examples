// Copyright (C) 2014 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.usagedatalogger;

import com.google.gerrit.extensions.events.UsageDataPublishedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class UsageDataLogger implements UsageDataPublishedListener {

  private static final Logger log = LoggerFactory.getLogger(UsageDataLogger.class);

  @Override
  public void onUsageDataPublished(Event event) {
    if (log.isInfoEnabled()) {
      log.info(
          String.format(
              "Usage data for " + "%s at %s",
              event.getMetaData().getDescription(), event.getInstant()));
      log.info(String.format("project name - %s", event.getMetaData().getName()));
      String unitSymbol = event.getMetaData().getUnitSymbol();
      for (Data data : event.getData()) {
        log.info(String.format("%s - %d %s", data.getProjectName(), data.getValue(), unitSymbol));
      }
      log.info("");
    }
  }
}
