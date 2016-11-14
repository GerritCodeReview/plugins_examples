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
    function onSayHelloProject(c) {
      var f = c.textfield();
      var t = c.checkbox();
      var b = c.button('Say hello', {onclick: function(){
        c.call(
          {message: f.value, french: t.checked},
          function(r) {
            c.hide();
            window.alert(r);
            c.refresh();
          });
      }});
      c.popup(c.div(
        c.prependLabel('Greeting message', f),
        c.br(),
        c.label(t, 'french'),
        c.br(),
        b));
      f.focus();
    }
    self.onAction('project', 'hello-project', onSayHelloProject);
  });
