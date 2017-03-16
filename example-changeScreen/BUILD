load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.changescreen.HelloForm"

gerrit_plugin(
    name = "example-changeScreen",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-changeScreen",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.changescreen.Module",
        "Implementation-Title: Example Change Screen",
    ],
    resources = glob(["src/main/**/*"]),
)
