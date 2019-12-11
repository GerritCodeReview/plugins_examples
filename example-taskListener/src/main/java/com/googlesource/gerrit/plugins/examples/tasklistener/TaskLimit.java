// Copyright (C) 2019 The Android Open Source Project
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
package com.googlesource.gerrit.plugins.examples.tasklistener;

import com.google.gerrit.server.git.WorkQueue.Task;
import com.google.gerrit.server.git.WorkQueue.TaskListener;
import com.google.inject.Singleton;
import java.util.concurrent.Semaphore;

@Singleton
public class TaskLimit implements TaskListener {
  private static final String TASK_NAME_REGEX = "git-upload-pack.*";

  private final Semaphore semaphore = new Semaphore(3, true);

  @Override
  public void onStart(Task<?> task) {
    if (task.toString().matches(TASK_NAME_REGEX)) {
      semaphore.acquireUninterruptibly();
    }
  }

  @Override
  public void onStop(Task<?> task) {
    if (task.toString().matches(TASK_NAME_REGEX)) {
      semaphore.release();
    }
  }
}
