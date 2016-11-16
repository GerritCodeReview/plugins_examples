load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshCommandOption",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshCommandOption",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshcommandoption.SshModule",
        "Implementation-Title: Example Simple SSH Command Option",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
