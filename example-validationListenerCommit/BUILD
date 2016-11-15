load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerCommit",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerCommit",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenercommit.Module",
        "Implementation-Title: Example Validation Listener Commit",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
