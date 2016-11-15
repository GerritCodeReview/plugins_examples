load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerNewProjectCreated",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerNewProjectCreated",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenernewprojectcreated.Module",
        "Implementation-Title: Example Validation Listener New Project Created",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
