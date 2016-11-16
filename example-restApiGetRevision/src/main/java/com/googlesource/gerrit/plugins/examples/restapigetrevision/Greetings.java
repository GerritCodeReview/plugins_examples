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

package com.googlesource.gerrit.plugins.examples.restapigetrevision;

import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.server.change.RevisionResource;

import java.util.ArrayList;
import java.util.Collection;

class Greetings implements RestReadView<RevisionResource> {

  @Override
  public Response<Collection<GreetInfo>> apply(RevisionResource rev) {
    Collection<GreetInfo> l = new ArrayList<>(3);
    l.add(new GreetInfo("Bonjour", "France",
        "http://en.wikipedia.org/wiki/France"));
    l.add(new GreetInfo("Hallo", "Germany",
        "http://en.wikipedia.org/wiki/Germany"));
    l.add(new GreetInfo("Hello", "USA",
        "http://en.wikipedia.org/wiki/USA"));
    return Response.ok(l);
  }

  static class GreetInfo {
    String message;
    String country;
    String href;

    GreetInfo(String m, String c, String h) {
      message = m;
      country = c;
      href = h;
    }
  }
}
