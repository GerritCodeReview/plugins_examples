genrule(
    name = "all",
    srcs = [
        "//example-changeOperator",
        "//example-consoleMetricReporter",
        "//example-simpleSshCommand",
        "//example-sshCommandAlias",
        "//example-topMenu",
        "//example-usageDataLogger",
        "//example-webLinkBranch",
        "//example-webLinkFileHistory",
        "//example-webLinkPatchSet",
        "//example-webLinkProject",
    ],
    outs = ["all.zip"],
    cmd = " && ".join([
        "p=$$PWD",
        "t=$$(mktemp -d || mktemp -d -t bazel-tmp)",
        "cp $(SRCS) $$t",
        "cd $$t",
        "zip -qr $$p/$@ .",
    ]),
)
