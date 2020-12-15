# PolyGerrit Add section of metadata to change view left panel

Adds section of metadata to change view left panel using change-metadata-item
extension point, which is located on the bottom of the change view left panel.



## How to install

There is nothing to build, just copy the gerrit-change-metadataItem.html into
the Gerrit's /plugins directory. There is no need to restart anything,
once the file gets copied, Gerrit will detect the new PolyGerrit plugin after
a few moments and will use it transparently.
