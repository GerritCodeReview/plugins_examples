load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-simpleSshCommand",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-simpleSshCommand",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.simplesshcommand.SshModule",
        "Implementation-Title: Example Simple SSH Command",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/examples/example-simpleSshCommand",
    ],
    resources = glob(["src/main/**/*"]),
)

