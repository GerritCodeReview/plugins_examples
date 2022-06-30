load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-restapiservlet",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-restApiServlet",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.restapiservlet.HttpModule",
        "Implementation-Title: Example RestApiServlet",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
