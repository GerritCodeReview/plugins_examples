load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-simpleSshCommandExtended",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-simpleSshCommandExtended",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.simplesshcommandextended.Modules$SshModule",
        "Implementation-Title: Example Simple SSH Command",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
