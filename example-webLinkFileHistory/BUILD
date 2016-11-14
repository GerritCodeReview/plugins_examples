load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-webLinkFileHistory",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-webLinkFileHistory",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.weblinkfilehistory.Module",
        "Implementation-Title: Example Web Link File History",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
