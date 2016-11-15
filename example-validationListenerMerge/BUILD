load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerMerge",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerMerge",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenermerge.Module",
        "Implementation-Title: Example Validation Listener Merge",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
