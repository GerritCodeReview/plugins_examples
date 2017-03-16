genrule(
    name = "all",
    srcs = [
        "//example-adminSshCommand",
        "//example-changeHasOperator",
        "//example-changeOperator",
        "//example-commitValidator",
        "//example-consoleMetricReporter",
        "//example-deployedOnIncludedInExtension",
        "//example-restApiGetRevision",
        "//example-restApiPostProject",
        "//example-restApiPostRevision",
        "//example-servlet",
        "//example-simpleSshCommand",
        "//example-sshCommandAlias",
        "//example-topMenu",
        "//example-usageDataLogger",
        "//example-validationListenerAssignee",
        "//example-validationListenerCommit",
        "//example-validationListenerHashtag",
        "//example-validationListenerMerge",
        "//example-validationListenerNewProjectCreated",
        "//example-validationListenerRefOperation",
        "//example-validationListenerUpload",
        "//example-webLinkBranch",
        "//example-webLinkFileHistory",
        "//example-webLinkPatchSet",
        "//example-webLinkProject",
        "//example-wuiChangeScreenBelowChangeInfoBlock",
        "//example-wuiChangeScreenHeader",
        "//example-wuiChangeScreenHeaderRightOfButtons",
        "//example-wuiChangeScreenHeaderRightOfPopDowns",
        "//example-wuiPreferenceScreenBottom",
        "//example-wuiProfileExtension",
        "//example-wuiSettingsScreen",
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
