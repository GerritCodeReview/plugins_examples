load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuichangescreennew.HelloForm"

gerrit_plugin(
    name = "example-wuiChangeScreenNew",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiChangeScreenNew",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuichangescreennew.Module",
        "Implementation-Title: Example Change Screen",
    ],
    resources = glob(["src/main/**/*"]),
)
