load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-validationListenerHashtag",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-validationListenerHashtag",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.validationlistenerhashtag.Module",
        "Implementation-Title: Example Validation Listener Hashtag",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
