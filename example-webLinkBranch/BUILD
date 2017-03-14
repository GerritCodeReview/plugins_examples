load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-webLinkBranch",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-webLinkBranch",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.weblinkbranch.Module",
        "Implementation-Title: Example Web Link Branch",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
