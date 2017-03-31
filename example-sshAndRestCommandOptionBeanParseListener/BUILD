load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshAndRestCommandOptionBeanParseListener",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshAndRestCommandOptionBeanParseListener",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistner.SshModule",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistner.HttpModule",
        "Implementation-Title: Example Simple SSH and REST Command Option Bean Parse Listner",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
