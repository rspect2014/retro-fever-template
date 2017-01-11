# retro-fever

A Leiningen template for the retro-fever game engine to get you up and running
quickly. The template will create the following:

  * `project.clj` - with the necessary dependencies and cljsbuild configuration
  * `core.cljs` - The main entry point for the game code
  * `index.html` - A basic HTML page to load and run the game

Optional integration to [Figwheel][] and [Weasel][] for a more interactive
development experience.

[Figwheel]: https://github.com/bhauman/lein-figwheel
[Weasel]: https://github.com/tomjakubowski/weasel

## Usage

```
lein new retro-fever <project-name> <feature>
```

The `feature` arguments is optional but will provide alternative
`project.clj` and `core.cljs` files prepared for the wanted integration
if specified:

### Engine features

  * `-scene` - Sets up a basic retro-fever game with update and render functions
               instead of using a scene graph

### Development feature

  * `+figwheel` - Adds support for figwheel which dynamically loads changes in cljs
                  files and gives you a connected ClojureScript REPL.

  * `+weasel` - Add support for weasel, a interactive browser connected ClojureScript
                REPL using websockets

Usage example with "rf-test" as desired project name:

    lein new retro-fever rf-test +scene +figwheel +weasel

Make sure to check out the `README.md` file in the project directory that the
template creates, for instructions on how to get started.

## Acknowledgements

Large portions of this template is based on the luminus-template project
Copyright © 2016 Yogthos

## License

Copyright © 2017 RetroSpect

Distributed under the Eclipse Public License either version 1.0 or any later
version.
