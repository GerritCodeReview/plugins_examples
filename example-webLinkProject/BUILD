load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-webLinkProject",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-webLinkProject",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.weblinkproject.Module",
        "Implementation-Title: Example Web Link Project",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
