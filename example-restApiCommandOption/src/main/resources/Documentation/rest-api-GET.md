@PLUGIN@
==========

NAME
----
@PLUGIN@ - Say Hello in the Log

SYNOPSIS
--------
     GET /changes/?--@PLUGIN@--log

DESCRIPTION
-----------
Prints "Say Hello in the Log" in the Log.


ACCESS
------
Any authenticated user.

EXAMPLES
--------

Have the server say Hello to the user

>     curl -X GET --digest --user joe:secret http://host:port/a/changes/?--@PLUGIN@--log

LOG:
> [HTTP-69] ERROR com.googlesource.gerrit.plugins.examples.restapicommandoption.HttpModule : Say Hello in the Log

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)
* [REST API Development](../../../Documentation/dev-rest-api.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
