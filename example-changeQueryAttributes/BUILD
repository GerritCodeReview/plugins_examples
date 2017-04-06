load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-changeQueryAttributes",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-changeQueryAttributes",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.changequeryattributes.Module",
        "Implementation-Title: Example Simple Change Query Attribute",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
