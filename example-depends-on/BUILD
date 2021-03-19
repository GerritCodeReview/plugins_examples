load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-depends-on",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-depends-on",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.dependson.Module",
        "Implementation-Title: Example depends on",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
