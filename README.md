# Contour

Google Maps in Clojurescript! Very early days.

## Tools

This project rides on [Noir](https://github.com/ibdknox/noir) and the new and wonderful [ClojureScript](https://github.com/clojure/clojurescript) project.

To get started, you'll need the following tools

* Leiningen (Build tool for clojure, located [on github](https://github.com/technomancy/leiningen)
* Clojurescript
* [cljs-watch](https://github.com/ibdknox/cljs-watch)

### This project

    git clone https://sritchie@github.com/sritchie/contour.git
    cd contour

### Leiningen

Copied from the Leiningen README:

* [Download the script.](https://raw.github.com/technomancy/leiningen/stable/bin/lein)
* Place it on your path. (I like to use `~/bin`)
* Set it to be executable. (`chmod 755 ~/bin/lein`)

### Clojurescript

Pick a root directory (rootdir) where clojurescript can live and run the following:

    cd contour # from above
    sh bootstrap.sh /path/to/rootdir

And follow the instructions at the end of the install.

### cljs-watch

cljs-watch is a small script that watches your source directory for changes to clojurescript files. When it sees a change, it recompiles everything. Change your source, reload the browser, and you're set! The install is the same as for leiningen, with a different script:

* [Download the script.](https://github.com/ibdknox/cljs-watch/blob/master/cljs-watch)
* Place it on your path. (I like to use `~/bin`)
* Set it to be executable. (`chmod 755 ~/bin/lein`)

## Usage

Use two terminals. In the first:

```bash
    cd contour
    lein deps
    lein ring server    
```

And in the second:

```bash
    cd contour
    cljs-watch
```

## Next Steps

## License

Copyright (C) 2011 Sam Ritchie

Distributed under the Eclipse Public License, the same as Clojure.

## Notes (NOT meant to make sense!)

https://github.com/whizbangsystems/innovation-fund-js/blob/master/src/forma-data/static/config.js

More on Overlays:
    http://code.google.com/apis/maps/documentation/javascript/overlays.html#CustomOverlays

* Add MarkerClusterer to externs.

http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js

* Read about what externs actually are :)
* Redo robin's utils.

