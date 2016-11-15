load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerRefOperation",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerRefOperation",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenerrefoperation.Module",
        "Implementation-Title: Example Validation Listener Ref Operation",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
