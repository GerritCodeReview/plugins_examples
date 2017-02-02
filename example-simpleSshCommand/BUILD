load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-simpleSshCommand",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-simpleSshCommand",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.simplesshcommand.SshModule",
        "Implementation-Title: Example Simple SSH Command",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
