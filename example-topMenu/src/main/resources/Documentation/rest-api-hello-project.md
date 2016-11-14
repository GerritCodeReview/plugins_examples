@PLUGIN@ hello-project
=============================

NAME
----
hello-project - Print our "Hello <user>!" message

SYNOPSIS
--------
>     POST /projects/{project-name}/cookbook~hello-project

DESCRIPTION
-----------
Prints "Hello <user>!".

OPTIONS
-------

--french
> Translate to French.

--message
> Greeting Message

ACCESS
------
Any authenticated user.

EXAMPLES
--------

Have the server say Hello to the user

>     curl -X POST --digest --user joe:secret http://host:port/a/projects/foo/cookbook~hello-project
> "Hello joe from project foo!"

Have the server say Bonjour to François

>     curl -X POST -H "Content-Type: application/json" \
>       -d '{message: "François", french: true}' \
>       --digest --user joe:secret \
>       http://host:port/a/projects/foo/cookbook~hello-project
> "Bonjour François from project foo!"

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)
* [REST API Development](../../../Documentation/dev-rest-api.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
