load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuichangescreenbelowchangeinfoblock.HelloForm"

gerrit_plugin(
    name = "example-wuiChangeScreenBelowChangeInfoBlock-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiChangeScreenBelowChangeInfoBlock-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuichangescreenbelowchangeinfoblock.Module",
        "Implementation-Title: Example Change Screen Extension Below Change Info Block",
    ],
    resources = glob(["src/main/**/*"]),
)
