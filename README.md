# cludoku

A project to solve sudoku puzzles, but mainly to learn a bit of clojure. My brain already hurts.

## Installation

Download from the page you are most likely reading this on (github.com/stoolio/cludoku).

## Usage

There are a few utility funtions that can be used. If you run:

    $ lein repl
    ...
    => (require 'cludoku.core)
    => (cludoku.core/-main)

You will get an ugly printout of the what is possible for each square.

For whatever reason, `lein run` doesn't output anything. I will be looking into that.

Some day, I will bundle this up in a jar and you will be able to use the command below. That day is not today.

    $ java -jar cludoku-0.1.0-standalone.jar [args]

## Options

None yet. The puzzle is current hardcoded, so if you want to ~~solve a different puzzle~~ get an unformatted blob of possibilities based on an incomplete algorithm, you have to edit `puzzle.txt` in the root.

## Examples

```
(def puzzle
  (load-puzzle "puzzle.txt"))

(possibles puzzle 3 2) ; => #{3 2 9 5}
```

### Bugs

There are no tests whatsoever. In fact the only tests there are were created by `lein new app` and are failing. That sounds like a double failure.

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
