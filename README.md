# What is Contour?

Contour is Google Maps v3 web app written entirely in ClojureScript! Very early days. We have a [demo](http://contour.herokuapp.com) on Heroku.

![](http://i.imgur.com/c3yDI.png)


# Overview

Contour is Google Maps v3 web app written entirely in ClojureScript. In other words, it's awesome.

The project rides on [Noir](https://github.com/ibdknox/noir), a framework for writing web applications in Clojure.

It also rides on the wonderful [ClojureScript](https://github.com/clojure/clojurescript) project which is a compiler for Clojure that emits JavaScript code! For bonus points it's also compatible with the with the advanced compilation mode of [Google Closure](http://code.google.com/closure) optimizing compiler. 

# Show me CODE

Here's a side-by-side of JavaScript and ClojureScript. The "Hello World" of Google Maps in JavaScript looks like this: 

    var map;

    function initialize() {
        var myOptions = {
            zoom: 8,
            center: new google.maps.LatLng(-34.397, 150.644),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map_canvas'), myOptions);
    }
    
    google.maps.event.addDomListener(window, 'load', initialize);

The "Hello World" of Google Maps in ClojureScript looks like this: 
     
    (def *map* nil)

    (def map-opts
      {:zoom 8
       :mapTypeId google.maps.MapTypeId.ROADMAP
       :center (google.maps.LatLng. -34.397, 150.644)})

    (defn init-map  [element overlays]
      (let [options (u/clj->js map-opts)
            map (google.maps.Map. element options)]))

    (defn map-load []
      (set! *map* (init-map
                  (goog.dom/getElement "map_canvas"))))

    (events/listen js/window "load"
                   map-load)


# Let's get started

To get started, you'll need to install a few tools, but it's painless.

* Contour (this project)
* Leiningen (Build tool for clojure, located [on github](https://github.com/technomancy/leiningen))
* ClojureScript
* [cljs-watch](https://github.com/ibdknox/cljs-watch)

## Contour

Fire up your command line and:

    git clone https://github.com/sritchie/contour.git
    cd contour

## Leiningen

Next install Leiningen, the build tool for Clojure. These instructions are copied from the Leiningen README:

* [Download this script](https://raw.github.com/technomancy/leiningen/stable/bin/lein) which is named `lein`
* Place it on your path so that you can execute it. (I like to use `~/bin`)
* Set it to be executable. (`chmod 755 ~/bin/lein`)

## ClojureScript

Next install ClojureScript. Pick a root directory (`rootdir`) where you want clojureScript to live and run the following commands:

    cd contour # from above
    sh bootstrap.sh /path/to/rootdir

And then just follow the instructions at the end of the install.

## cljs-watch

Next install cljs-watch. This is a small script that watches your source directory for changes to clojureScript files. When it sees a change, it recompiles everything for live updates. Change your source, reload the browser, and you're set! The install is the same as for leiningen, just with a different script:

* [Download the script.](https://github.com/ibdknox/cljs-watch/blob/master/cljs-watch)
* Place it on your path. (I like to use `~/bin`)
* Set it to be executable. (`chmod 755 ~/bin/lein`)

# Usage

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

Access [127.0.0.1:3000](127.0.0.1:3000) and your golden!

# License

Copyright © 2011 Sam Ritchie

Distributed under the Eclipse Public License, the same as Clojure.

# Notes (NOT meant to make sense!)

https://github.com/whizbangsystems/innovation-fund-js/blob/master/src/forma-data/static/config.js

More on Overlays:
    http://code.google.com/apis/maps/documentation/javascript/overlays.html#CustomOverlays

* Add MarkerClusterer to externs.

http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js

* Read about what externs actually are :)
* Redo robin's utils.