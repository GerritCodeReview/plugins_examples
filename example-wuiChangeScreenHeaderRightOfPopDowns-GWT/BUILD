load("//tools/bzl:plugin.bzl", "gerrit_plugin")

MODULE = "com.googlesource.gerrit.plugins.examples.wuichangescreenheaderrightofpopdowns.HelloForm"

gerrit_plugin(
    name = "example-wuiChangeScreenHeaderRightOfPopDowns-GWT",
    srcs = glob(["src/main/java/**/*.java"]),
    gwt_module = MODULE,
    manifest_entries = [
        "Gerrit-PluginName: example-wuiChangeScreenHeaderRightOfPopDowns-GWT",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuichangescreenheaderrightofpopdowns.Module",
        "Implementation-Title: Example Change Screen Header Extension Right Of Pop Downs",
    ],
    resources = glob(["src/main/**/*"]),
)
