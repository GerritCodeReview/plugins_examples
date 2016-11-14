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

Gerrit.install(function(self) {
    function onShowChange(c, r) {
      console.log("Show change: "
          + c.id
          + ", revision: " + r.name);
    }
    function onSubmitSomeoneElsesChange(c, r) {
        var u = self.getCurrentUser();
        if (u._account_id != c.owner._account_id) {
          return confirm("Really submit change:\n"
              + c.id + "\n"
              + "revision: " + r.name + "\n"
              + "from: " + c.owner.name
              + "?");
        }
        return true;
    }
    function onHistory(t) {
      console.log("History: " + t);
    }
    Gerrit.on('showchange', onShowChange);
    Gerrit.on('submitchange', onSubmitSomeoneElsesChange);
    Gerrit.on('history', onHistory);
  });
