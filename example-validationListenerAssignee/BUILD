load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerAssignee",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerAssignee",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenerassignee.Module",
        "Implementation-Title: Example Validation Listener Assignee",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
