load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-restApiGetRevision",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-restApiGetRevision",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.restapigetrevision.Module",
        "Implementation-Title: Example Rest Api Get Revision",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
