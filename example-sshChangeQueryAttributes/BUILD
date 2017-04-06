load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshChangeQueryAttributes",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshChangeQueryAttributes",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.sshchangequeryattributes.Module",
        "Implementation-Title: Example Simple SSH Change Query Attribute",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
