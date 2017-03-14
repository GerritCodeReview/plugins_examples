load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-commitValidator",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-commitValidator",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.commitvalidator.Module",
        "Implementation-Title: Example Commit Validator",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
