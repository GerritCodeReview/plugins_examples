load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshAndRestCommandOptionBeanParseListener",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshAndRestCommandOptionBeanParseListener",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistener.Modules$SshModule",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistener.Modules$HttpModule",
        "Implementation-Title: Example Simple SSH and REST Command Option Bean Parse Listener",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
