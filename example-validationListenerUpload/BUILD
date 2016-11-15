load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerUpload",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerUpload",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenerupload.Module",
        "Implementation-Title: Example Validation Listener Upload",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
