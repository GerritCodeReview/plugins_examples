@PLUGIN@
==============================

NAME
----
hello-revision - Print "Hello" in multiple languages

SYNOPSIS
--------
>     GET /changes/{change-id}/revisions/{revision-sha}/@PLUGIN@

DESCRIPTION
-----------
Outputs JSON object with "Hello" in multiple languages


ACCESS
------
Any authenticated user.

EXAMPLES
--------

Have the server say Hello to the user

>     curl -X GET --digest --user joe:secret \
            http://host:port/a/changes/1/revisions/f6fb8e7454113c0bc7013163d313e6ecd90e0de4/@PLUGIN@~greetings

Response:

  [
    {
      "message": "Bonjour",
      "country": "France",
      "href": "http://en.wikipedia.org/wiki/France"
    },
    {
      "message": "Hallo",
      "country": "Germany",
      "href": "http://en.wikipedia.org/wiki/Germany"
    },
    {
      "message": "Hello",
      "country": "USA",
      "href": "http://en.wikipedia.org/wiki/USA"
    }
  ]


SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)
* [REST API Development](../../../Documentation/dev-rest-api.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
