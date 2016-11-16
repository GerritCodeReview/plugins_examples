load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-changeHasOperator",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-changeHasOperator",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.changehasoperator.Module",
        "Implementation-Title: Example Change Has Operator",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
