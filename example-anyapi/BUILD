load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS", "gerrit_plugin")

gerrit_plugin(
    name = "example-anyapi",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-anyapi",
        "Gerrit-ApiModule: com.googlesource.gerrit.plugins.examples.anyapi.ApiModule",
        "Implementation-Title: Example of any API binding",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "//lib/auto:auto-value-gson",
    ],
)
