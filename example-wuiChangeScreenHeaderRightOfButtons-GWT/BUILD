load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuichangescreenheaderrightofbuttons.HelloForm"

gerrit_plugin(
    name = "example-wuiChangeScreenHeaderRightOfButtons-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiChangeScreenHeaderRightOfButtons-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuichangescreenheaderrightofbuttons.Module",
        "Implementation-Title: Example Change Screen Header Extension Right Of Buttons",
    ],
    resources = glob(["src/main/**/*"]),
)
