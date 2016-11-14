load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-webLinkPatchSet",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-webLinkPatchSet",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.weblinkpatchset.Module",
        "Implementation-Title: Example Web Link Patch Set",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
