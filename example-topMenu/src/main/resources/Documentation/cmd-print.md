@PLUGIN@ print
=====================

NAME
----
@PLUGIN@ print - Print our "Hello world" message

SYNOPSIS
--------
>     ssh -p <port> <host> @PLUGIN@ print
>      [--french]
>      [name]

DESCRIPTION
-----------
Prints "Hello world!" or optionally "Bonjour world!".

OPTIONS
-------

--french
> Translate to French.

--help

-h
> Display usage information.

name
> Display given name instead of 'world'.

ACCESS
------
Any user who has configured an SSH key.

SCRIPTING
---------
This command is intended to be used in scripts.

EXAMPLES
--------

Have the server say hi to the world.

>     $ ssh -p 29418 review.example.com @PLUGIN@ print

Have the server say hi to the world in French.

>     $ ssh -p 29418 review.example.com @PLUGIN@ print --french

Have the server say hi to you in French.

>     $ ssh -p 29418 review.example.com @PLUGIN@ print --french \'Revi Ewer\'

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
