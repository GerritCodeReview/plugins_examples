genrule(
    name = "all",
    srcs = [
        "//example-adminSshCommand",
        "//example-changeOperator",
        "//example-commitValidator",
        "//example-restApiGetRevision",
        "//example-restApiPostProject",
        "//example-restApiPostRevision",
        "//example-simpleSshCommand",
        "//example-sshCommandAlias",
        "//example-topMenu",
        "//example-usageDataLogger",
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
