load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuiprofileextensionbottom.HelloForm"

gerrit_plugin(
    name = "example-wuiProfileExtensionBottom-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiProfileExtensionBottom-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuiprofileextensionbottom.Module",
        "Implementation-Title: Example Profile Extension Bottom",
    ],
    resources = glob(["src/main/**/*"]),
)
