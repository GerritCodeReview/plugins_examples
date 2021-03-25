load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS", "gerrit_plugin")

gerrit_plugin(
    name = "example-depends-on",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Implementation-Title: example-depends-on",
        "Implementation-URL: https://github.com/lucamilanesio/example-depends-on",
        "Gerrit-PluginName: example-depends-on",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.dependson.Module",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        ":shared-plugin-environment-neverlink",
        "//lib/auto:auto-value-gson",
    ],
)

java_library(
    name = "shared-plugin-environment-neverlink",
    neverlink = 1,
    exports = ["//plugins/shared-plugin-environment"],
)
