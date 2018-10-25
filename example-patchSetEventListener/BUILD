load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-patchSetEventListener",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-patchSetEventListener",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.patchseteventlistener.Module",
        "Implementation-Title: Example PatchSet EventListener",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
