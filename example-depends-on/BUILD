load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS", "gerrit_plugin")

gerrit_plugin(
    name = "example-depends-on",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-depends-on",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.dependson.Module",
        "Implementation-Title: Example depends on",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "//lib/auto:auto-value-gson",
    ],
)
