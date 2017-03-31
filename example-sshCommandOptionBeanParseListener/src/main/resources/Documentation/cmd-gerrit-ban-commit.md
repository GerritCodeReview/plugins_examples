@PLUGIN@
=========

NAME
----
@PLUGIN@ - Logs messages when running the Gerrit SSH Command Ban-Commit

SYNOPSIS
--------
>     ssh -p <port> <host> gerrit ban-commit <PROJECT> <COMMIT> --@PLUGIN@--hammer <VAL>


DESCRIPTION
-----------
Outputs in Server Log (ERROR): three "!BANHAMMER!" messages

OPTIONS
-------

\-\-@PLUGIN@\-\-hammer
> three !BANHAMMER! messages in the Log.

ACCESS
------
Any user who has configured an SSH key.

SCRIPTING
---------
This command is intended for test purposes.

EXAMPLES
--------

Have the server say hi to the world.

>     $ ssh -p 29418 review.example.com gerrit ban-commit
>           tools/test/project0
>           8b0b75b90ea7b363410a5c5100aace55adfa3b7e
>           --example-sshCommandOptionBeanParseListner--hammer hi

LOG:
>     ERROR com.googlesource.gerrit.plugins.examples.sshcommandoptionbeanparselistner.SshModule : !BANHAMMER! - parse start
>     ERROR com.googlesource.gerrit.plugins.examples.sshcommandoptionbeanparselistner.SshModule : !BANHAMMER! - option parse hi
>     ERROR com.googlesource.gerrit.plugins.examples.sshcommandoptionbeanparselistner.SshModule : !BANHAMMER! - parse end

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
