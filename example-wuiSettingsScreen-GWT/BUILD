load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuisettingsscreen.HelloForm"

gerrit_plugin(
    name = "example-wuiSettingsScreen-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiSettingsScreen-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuisettingsscreen.Module",
        "Implementation-Title: Example Settings Screen Extension",
    ],
    resources = glob(["src/main/**/*"]),
)
