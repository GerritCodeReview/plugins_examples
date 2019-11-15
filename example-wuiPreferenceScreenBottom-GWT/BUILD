load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuipreferencescreenbottom.HelloForm"

gerrit_plugin(
    name = "example-wuiPreferenceScreenBottom-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiPreferenceScreenBottom-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuipreferencescreenbottom.Module",
        "Implementation-Title: Example Preference Screen Panel Bottom",
    ],
    resources = glob(["src/main/**/*"]),
)
