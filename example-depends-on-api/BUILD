load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS", "gerrit_plugin")

gerrit_plugin(
    name = "example-depends-on-api",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-depends-on-api",
        "Gerrit-ApiModule: com.googlesource.gerrit.plugins.examples.dependson.ApiModule",
        "Implementation-Title: Example depends on API",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "//lib/auto:auto-value-gson",
    ],
)
