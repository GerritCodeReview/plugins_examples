load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-denyUpload",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-denyUpload",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.denyupload.Module",
        "Implementation-Title: Deny Upload Example",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
