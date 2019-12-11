load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-taskThrottle",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-taskThrottle",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.taskthrottle.Module",
        "Implementation-Title: Example WorkQueue.TaskThrottle",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
