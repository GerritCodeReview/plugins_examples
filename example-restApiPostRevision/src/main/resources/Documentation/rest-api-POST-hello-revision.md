@PLUGIN@ hello-revision
==============================

NAME
----
hello-revision - Print our "Hello <user>!" message

SYNOPSIS
--------
     POST /changes/{change-id}/revisions/{revision-sha}/@PLUGIN@~hello-revision

DESCRIPTION
-----------
Prints "Hello <user> from change <change-id>, patch set <patch-set>!".

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

>     curl -X POST --digest --user joe:secret \
            http://host:port/a/changes/f6fb8e7454113c0bc7013163d313e6ecd90e0de4/revisions/1/@PLUGIN@~hello-revision
> "Hello joe from change 1, patch set 1!"

Have the server say Bonjour to François

>     curl -X POST -H "Content-Type: application/json" \
>       -d '{message: "François", french: true}' \
>       --digest --user joe:secret \
>       http://host:port/a/changes/1/revisions/f6fb8e7454113c0bc7013163d313e6ecd90e0de4/@PLUGIN@~hello-revision
> "Bonjour François from change 1, patch set 1!"

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)
* [REST API Development](../../../Documentation/dev-rest-api.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
