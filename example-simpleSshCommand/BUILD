load("@com_github_davido_bazlets//:gerrit_plugin.bzl",
     "gerrit_plugin")
load("@com_github_davido_bazlets//:gerrit_api_neverlink.bzl",
     "gerrit_api_neverlink")

gerrit_plugin(
    name = "example-simpleSshCommand",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Gerrit-PluginName: example-simpleSshCommand",
        "Gerrit-SshModule: com.googlesource.gerrit.plugins.examples.simplesshcommand.SshModule",
        "Implementation-Title: Example Simple SSH Command",
    ],
)

