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

Gerrit.install(function(self) {
    function onOpenChange(c, r) {
      var url = "changes/"
          + c._number
          + "/revisions/"
          + r._number
          + "/"
          + self.getPluginName()
          + "~"
          + "greetings";
      var change_plugins = document
          .getElementById('change_plugins');
      Gerrit.get(url, function(r) {
         var doc = document;
         var frg = doc.createDocumentFragment();
         var table = doc.createElement('table');
         frg.appendChild(table);
         for (var i = 0; i < r.length; i++) {
           // row
           var tr = doc.createElement('tr');
           // greet
           var g = r[i];
           // first column: message
           var td = doc.createElement('td');
           td.appendChild(doc.createTextNode(g.message));
           tr.appendChild(td);
           // second column: country
           td = doc.createElement('td');
           var a = doc.createElement('a');
           a.href = g.href;
           a.appendChild(doc.createTextNode(g.country));
           td.appendChild(a);
           tr.appendChild(td);
           table.appendChild(tr);
         }
         // add frg to #change_plugins container
         change_plugins.appendChild(frg);
      });
    }
    Gerrit.on('showchange', onOpenChange);
  });
