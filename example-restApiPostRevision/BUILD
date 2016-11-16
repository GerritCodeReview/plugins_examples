load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-restApiPostRevision",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-restApiPostRevision",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.restapipostrevision.Module",
        "Implementation-Title: Example Rest Api Post Revision",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
