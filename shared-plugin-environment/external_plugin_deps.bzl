load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'groovy',
    artifact = 'org.codehaus.groovy:groovy-all:2.4.12',
    sha1 = '760afc568cbd94c09d78f801ce51aed1326710af',
  )
