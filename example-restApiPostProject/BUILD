load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-restApiPostProject",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-restApiPostProject",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.restapipostproject.Module",
        "Implementation-Title: Example Rest Api Post Project",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
