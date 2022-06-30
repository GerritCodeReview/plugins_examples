@PLUGIN@
==========

NAME
----
@PLUGIN@ - Say Hello in the Log

SYNOPSIS
--------
     GET /plugins/@PLUGIN@/hello

DESCRIPTION
-----------
Returns various "hello".

ACCESS
------
Anyone.

EXAMPLES
--------

List all the hello:

>     curl -X GET http://host:port/plugins/@PLUGIN@/hello

Output:

```
)]}'
{"german":"Hallo Welt","english":"hello world","french":"bonjour tout monde"}
```

With Gerrit REST API pretty printing:

>     curl -X GET 'http://host:port/plugins/@PLUGIN@/hello?pp=1'

Output:

```
)]}'
{
  "german": "Hallo Welt",
  "english": "hello world",
  "french": "bonjour tout monde"
}
```

SEE ALSO
--------

* [Plugin Development](../../../Documentation/dev-plugins.html)
* [REST API Development](../../../Documentation/dev-rest-api.html)

GERRIT
------
Part of [Gerrit Code Review](../../../Documentation/index.html)
