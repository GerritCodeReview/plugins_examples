#!/bin/bash

# This script will be run by bazel when the build process starts to
# generate key-value information that represents the status of the
# workspace. The output should be like
#
# KEY1 VALUE1
# KEY2 VALUE2
#
# If the script exits with non-zero code, it's considered as a failure
# and the output will be discarded.

function rev() {
  cd $1; git describe --always --match "v[0-9].*" --dirty
}

for p in example-* ; do
  test -d "$p" || continue
  # special treatment of nested example plugins
  echo STABLE_BUILD_$(echo $(basename $p)_LABEL|tr '[a-z]' '[A-Z]' ) $(rev $p)
done
