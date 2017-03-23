load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuiindexscreen.HelloForm"

gerrit_plugin(
    name = "example-wuiIndexScreen",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiIndexScreen",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuiindexscreen.Module",
        "Implementation-Title: Example Index Screen Extension",
    ],
    resources = glob(["src/main/**/*"]),
)
