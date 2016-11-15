genrule(
    name = "all",
    srcs = [
        "//example-changeOperator",
        "//example-simpleSshCommand",
        "//example-sshCommandAlias",
        "//example-topMenu",
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
