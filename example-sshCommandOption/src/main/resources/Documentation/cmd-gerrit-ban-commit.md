@PLUGIN@
=========

NAME
----
@PLUGIN@ - Logs a message when running the Gerrit SSH Command Ban-Commit

SYNOPSIS
--------
>     ssh -p <port> <host> gerrit ban-commit <PROJECT> <COMMIT> --@PLUGIN@--log <VAL>


DESCRIPTION
-----------
Output in Server Log (ERROR): "Say Hello in the Log test".

OPTIONS
-------

\-\-@PLUGIN@\-\-log
> Say Hello in the Log.

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
>           --example-sshCommandOption--log test

LOG:
>     ERROR com.googlesource.gerrit.plugins.examples.sshcommandoption.SshModule : Say Hello in the Log test

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
