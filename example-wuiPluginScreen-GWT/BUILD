load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuipluginscreen.HelloForm"

gerrit_plugin(
    name = "example-wuiPluginScreen-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiPluginScreen-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuipluginscreen.Module",
        "Implementation-Title: Example Plugin Screen Extension",
    ],
    resources = glob(["src/main/**/*"]),
)
