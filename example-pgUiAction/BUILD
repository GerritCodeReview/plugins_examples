load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-pgUiAction",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-pgUiAction",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.pguiaction.Module",
	"Gerrit-HttpModule: com.googlesource.gerrit.plugins.examples.pguiaction.HttpModule",
        "Implementation-Title: Example PG UiAction",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
