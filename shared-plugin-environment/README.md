Gerrit Shared Plugin Environment
================================

This plugin provides a shared environment for loading plugins that wants
to mixup between teach other.

To build, link this directory under Gerrit's tree plugins directory
and from the root of the gerrit tree run:

```
  bazel build plugins/shared-plugin-environment
```

The resulting artifact can be found under:

```
 bazel-bin/plugins/shared-plugin-environment/shared-plugin-environment.jar
```

To test deploy the review plugin [1] and copy this plugin
under `$gerrit_site/plugins` directory.
