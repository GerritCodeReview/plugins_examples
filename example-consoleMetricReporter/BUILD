load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-consoleMetricReporter",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-consoleMetricReporter",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.consolemetricreporter.Module",
        "Implementation-Title: Example consoleMetricReporter",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
