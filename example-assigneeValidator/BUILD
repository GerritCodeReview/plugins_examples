load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-assigneeValidator",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-assigneeValidator",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.assigneevalidator.Module",
        "Implementation-Title: Example Assignee Validator",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
