load("@rules_java//java:defs.bzl", "java_library")
load("//tools/bzl:junit.bzl", "junit_tests")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS", "gerrit_plugin")

gerrit_plugin(
    name = "example-pd",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Implementation-Title: example-pd",
        "Implementation-URL: https://github.com/lucamilanesio/example-pd",
        "Gerrit-PluginName: example-pd",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.pd.Modules$Module",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        ":example-depends-on-neverlink",
        ":shared-plugin-environment-neverlink",
        "//lib/auto:auto-value-gson",
    ],
)

java_library(
    name = "shared-plugin-environment-neverlink",
    neverlink = 1,
    exports = ["//plugins/shared-plugin-environment"],
)

java_library(
    name = "example-depends-on-neverlink",
    neverlink = 1,
    exports = ["//plugins/example-depends-on"],
)
