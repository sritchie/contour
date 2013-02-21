# What is Contour?

Contour is Google Maps v3 web app written entirely in ClojureScript! In other words, it's awesome.

Very early days. We have a [demo](http://contour.herokuapp.com) on Heroku.

![](http://i.imgur.com/c3yDI.png)

# Overview

The project rides on [Compojure](https://github.com/weavejester/compojure), a framework for writing web applications in Clojure.

It also rides on [ClojureScript](https://github.com/clojure/clojurescript) which is a compiler for Clojure that emits JavaScript code! For bonus points it's also compatible with the with the advanced compilation mode of [Google Closure](http://code.google.com/closure) optimizing compiler.

But wait, there's more! As an extra bonus, Contour additionally rides on [lein-cljsbuild](https://github.com/emezeske/lein-cljsbuild), a leiningen plugin that makes it easy (and quick) to compile ClojureScript source into JavaScript.  It's similar to [cljs-watch](https://github.com/ibdknox/cljs-watch) but uses lein instead of a standalone executable.


# Show me CODE

Here's a side-by-side of JavaScript and ClojureScript. The "Hello World" of Google Maps in JavaScript looks like this:

```javascript
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
```

The "Hello World" of Google Maps in ClojureScript looks like this:

```clojure
    (def *map* nil)

    (def my-opts
      {"zoom" 8
       "mapTypeId" google.maps.MapTypeId.ROADMAP
       "center" (google.maps.LatLng. -34.397, 150.644)})

    (defn map-load []
      (let [elem (goog.dom/getElement "map_canvas")]
        (set! *map* (google.maps.Map. elem my-opts))))

    (events/listen js/window "load"
                   map-load)
```

# Let's get started

To get started, you'll need to clone Contour and install [Leinigen](https://github.com/technomancy/leiningen), the build tool for Clojure.

## Clone Contour

Fire up your command line and:


```bash
$ git clone https://github.com/sritchie/contour.git
$ cd contour
```

## Install Leiningen

Next install Leiningen:

* [Download this script](https://raw.github.com/technomancy/leiningen/stable/bin/lein) which is named `lein`
* Place it on your path so that you can execute it. (I like to use `~/bin`)
* Set it to be executable. (`chmod 755 ~/bin/lein`)

Set this environment variable:

```bash
export RING_ENV='development'
```

# Usage

```bash
$ cd contour

# Download the dependencies:
$ lein deps

# Open a new terminal window and:
$ lein cljsbuild auto

# Open a new terminal window and:
$ lein ring server
```
That should open a browser window at [127.0.0.1:3000](127.0.0.1:3000). You're all set!

# License

Copyright © 2013 Sam Ritchie

Distributed under the Eclipse Public License, the same as Clojure.
