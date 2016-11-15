load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-changeOperator",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-changeOperator",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.changeoperator.Module",
        "Implementation-Title: Example Change Operator",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
