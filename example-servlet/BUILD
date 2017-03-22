load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-servlet",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-servlet",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.servlet.HttpModule",
        "Implementation-Title: Example Servlet",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
