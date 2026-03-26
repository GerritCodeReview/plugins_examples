load("//tools/bzl:junit.bzl", "junit_tests")
load(
    "//tools/bzl:plugin.bzl",
    "PLUGIN_DEPS",
    "PLUGIN_TEST_DEPS",
    "gerrit_plugin",
)

plugin_name = "cache-infinispan"

gerrit_plugin(
    name = plugin_name,
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "@caffeine//jar",
        "@error_prone_annotations//jar",
        "@infinispan-commons-spi//jar",
        "@infinispan-commons//jar",
        "@infinispan-component-annotations//jar",
        "@infinispan-component-processor//jar",
        "@infinispan-core//jar",
        "@infinispan-counter-api//jar",
        "@infinispan-logging-annotations//jar",
        "@infinispan-logging-processor//jar",
        "@jackson-core//jar",
        "@jakarta-transaction-api//jar",
        "@jboss-logging-annotations//jar",
        "@jboss-logging//jar",
        "@jboss-threads//jar",
        "@jgroups//jar",
        "@jspecify//jar",
        "@metainf-services//jar",
        "@protostream-processor//jar",
        "@protostream-types//jar",
        "@protostream//jar",
        "@reactive-streams//jar",
        "@rxjava//jar",
        "@wildfly-common//jar",
    ],
)

junit_tests(
    name = "cache-infinispan_tests",
    srcs = glob([
        "src/test/java/**/*IT.java",
        "src/test/java/**/*Test.java",
    ]),
    tags = [plugin_name],
    deps = [":cache-infinispan_test_deps"],
)

java_library(
    name = "cache-infinispan_test_deps",
    testonly = True,
    srcs = glob(
        ["src/test/java/**/*.java"],
        exclude = [
            "src/test/java/**/*IT.java",
            "src/test/java/**/*Test.java",
        ],
    ),
    visibility = ["//visibility:public"],
    exports = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [plugin_name],
    deps = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [plugin_name],
)
