load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "example-taskListener",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: example-taskListener",
        "Gerrit-Module: com.googlesource.gerrit.plugins.examples.tasklistener.Module",
        "Implementation-Title: Example WorkQueue.TaskListener",
    ],
    resources = glob(["src/main/resources/**/*"]),
)
