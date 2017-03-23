load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-wuiJSExamples",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-wuiJSExamples",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.wuijsexamples.Module",
        "Implementation-Title: Example JavaScript Extensions",
    ],
    resources = glob(["src/main/**/*"]),
)
