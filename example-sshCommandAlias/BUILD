load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshCommandAlias",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshCommandAlias",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshcommandalias.SshModule",
        "Implementation-Title: Example SSH Command Alias",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
