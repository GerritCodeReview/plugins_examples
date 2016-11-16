load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-restApiCommandOption",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-restApiCommandOption",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.restapicommandoption.HttpModule",
        "Implementation-Title: Example Simple REST Command Option",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
