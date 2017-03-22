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

package com.googlesource.gerrit.plugins.examples.servlet;

import com.google.gerrit.extensions.annotations.PluginCanonicalWebUrl;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class HelloWorldServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final Logger log = LoggerFactory.getLogger(HelloWorldServlet.class);

  @Inject
  HelloWorldServlet(@PluginName String pluginName,
      @PluginCanonicalWebUrl String url) {
    log.info(String.format("Cookbook Plugin '%s' at url %s", pluginName, url));
  }

  @Override
  protected void doGet(final HttpServletRequest req,
      final HttpServletResponse rsp) throws IOException, ServletException {
    rsp.setContentType("text/html");
    rsp.setCharacterEncoding("UTF-8");
    try (Writer out = rsp.getWriter()) {
      out.write("<html>");
      out.write("<body>");
      out.write("<h2>Hello world!</h2>");
      out.write("</body>");
      out.write("</html>");
    }
  }
}
