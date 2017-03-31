load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshCommandOptionBeanParseListener",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshCommandOptionBeanParseListener",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshcommandoptionbeanparselistner.SshModule",
        "Implementation-Title: Example Simple SSH Command Option Bean Parse Listner",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
