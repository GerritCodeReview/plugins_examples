# Build

This plugin is built with Bazel in-tree build.

## Build in Gerrit tree

Create a symbolic link of the repository source to the Gerrit source
tree /plugins/cache-infinispan directory, and the external_plugin_deps.bzl
dependencies linked to /plugins/external_plugin_deps.bzl.

Example:

```sh
git clone https://gerrit.googlesource.com/gerrit
git clone https://gerrit.googlesource.com/modules/cache-infinispan
cd gerrit/plugins
ln -s ../../cache-infinispan .
ln -sf ../../cache-infinispan/external_plugin_deps.bzl .
```

From the Gerrit source tree issue the command `bazelisk build plugins/cache-infinispan`.

Example:

```sh
bazelisk build plugins/cache-infinispan
```

The output jar file is created under `bazel-bin/plugins/cache-infinispan/cache-infinispan.jar`

To execute the tests run `bazelisk test plugins/cache-infinispan/...` from the Gerrit source tree.

Example:

```sh
bazelisk test plugins/cache-infinispan/...
```

