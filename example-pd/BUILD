load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-pd",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-pd",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.pd.Modules$Module",
        "Implementation-Title: Example pd",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
