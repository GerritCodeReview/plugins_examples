load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuichangescreenheader.HelloForm"

gerrit_plugin(
    name = "example-wuiChangeScreenHeader-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiChangeScreenHeader-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuichangescreenheader.Module",
        "Implementation-Title: Example Change Screen Header Extension",
    ],
    resources = glob(["src/main/**/*"]),
)
