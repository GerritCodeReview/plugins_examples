load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "shared-plugin-environment",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Implementation-Title: Shared Plugin Environment",
        "Implementation-URL: https://github.com/lucamilanesio/shared-plugin-environment",
        "Gerrit-PluginName: shared-plugin-environment",
        "Gerrit-Module: com.googlesource.gerrit.plugins.sharedenvironment.Module",
    ],
    deps = [ ],
)
