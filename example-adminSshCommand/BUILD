load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-adminSshCommand",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-adminSshCommand",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.adminsshcommand.SshModule",
        "Implementation-Title: Example Admin SSH Command",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
