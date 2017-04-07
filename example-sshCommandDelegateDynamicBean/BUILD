load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-sshCommandDelegateDynamicBean",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-sshCommandDelegateDynamicBean",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.sshcommanddelegatedynamicbean.SshModule",
        "Implementation-Title: Example SSH Command Delegate Dynamic Bean",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
