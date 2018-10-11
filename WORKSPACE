workspace(name = "examples")

load("//:bazlets.bzl", "load_bazlets")

load_bazlets(
    commit = "13330934a0e577d60719551ffb746f64b0bb9637",
    #    local_path = "/home/<user>/projects/bazlets",
)

# Release Plugin API
#load(
#    "@com_googlesource_gerrit_bazlets//:gerrit_api.bzl",
#    "gerrit_api",
#)

# Snapshot Plugin API
load(
    "@com_googlesource_gerrit_bazlets//:gerrit_api_maven_local.bzl",
    "gerrit_api_maven_local",
)
load(
    "@com_googlesource_gerrit_bazlets//:gerrit_gwt.bzl",
    "gerrit_gwt",
)

# Load release Plugin API
#gerrit_api()

# Load snapshot Plugin API
gerrit_api_maven_local()

gerrit_gwt()
