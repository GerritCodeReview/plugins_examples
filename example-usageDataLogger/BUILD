load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-usageDataLogger",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-usageDataLogger",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.usagedatalogger.Module",
        "Implementation-Title: Example Usage Data Logger",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
