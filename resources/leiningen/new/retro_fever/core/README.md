# {{name}}

Depending on the chosen integration you will initialize the development
environment in different ways:


## No integration

If you don't use any integration make sure that the ClojureScript has been
build:

    lein cljsbuild once

And open `resources/public/index.html` in a browser and watch for the text
"Launching game" in the browser console.

<% if figwheel? %>
## Using Figwheel

From the project directory start Figwheel (which among other things also handle
cljs building):

    lein figwheel

It will take a little time for figwheel to start but afterwards open the
following url in a browser:

    http://localhost:3449/

Not specifying a file in the above URL will serve you `index.html` by default.
<% endif %>
<% if weasel? %>
## Using Weasel

Depending on the Weasel integration chosen upon project initialization, you will
need to follow the instructions described above in either "No integration"
(integration: `+weasel`) or "Using Figwheel" (integration: `+figwheel +weasel`).
But before opening the HTML file do the following.

Start a REPL connected to the project. If you can't or don't want to use a REPL
built into your editor (ie. Emacs with Cider jack-in) you can always do it from
`lein`:

    lein repl

Now start a ClojureScript REPL using Weasel:

``` clojure
user> (require 'weasel.repl.websocket)
nil
user> (cemerick.piggieback/cljs-repl
        :repl-env (weasel.repl.websocket/repl-env
                   :ip "0.0.0.0" :port 9001))

<< started Weasel server on ws://0.0.0.0:9001 >>
Type `:cljs/quit` to stop the ClojureScript REPL
nil
```

Now open the HTML as described in instructions above matching your chosen
integration.

From the ClojureScript REPL switch to you game namespace:

``` clojure
(ns {{name}}.core)
```

Now it's possible to start live interacting with the game ie. watch and change
the `game-state` atom among other things:

``` clojure
@game-state
(swap! game-state assoc :hey "ho")
```
<% endif %>