load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-deployedOnIncludedInExtension",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-deployedOnIncludedInExtension",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.deployedonincludedinextension.Module",
        "Implementation-Title: Example Deployed On Included-In Extension",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
