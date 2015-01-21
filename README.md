# retro-fever

A Leiningen template for the retro-fever game engine to get you up and running
quickly. The template will create the following:

  * `project.clj` with the necessary dependencies
  * `cljsbuild` configuration
  * index.html to load and run the empty game loop

Optional integration to [Figwheel][] and [Weasel][] for a more interactive
development experience.

[Figwheel]: https://github.com/bhauman/lein-figwheel
[Weasel]: https://github.com/tomjakubowski/weasel


## Usage

```
lein new retro-fever <project-name> [<integration>]
```

The `integration` argument is optional but will provide alternative
`project.clj` and `core.cljs` files prepared for the wanted integration
if specified:

  * `figwheel`
  * `weasel`
  * `figwheel+weasel`

Usage example with "bird-flapper" as desired project name:

    lein new retro-fever bird-flapper figwheel+weasel

Make sure to check out the `README.md` file in the project directory that the
template creates, for instructions on how to get started.

## Acknowledgements

Large portions of this template is based on the luminus-template project
Copyright © 2012 Yogthos

## License

Copyright © 2015 RetroSpect

Distributed under the Eclipse Public License either version 1.0 or any later
version.
