(ns contour.views
  (:use [environ.core :only (env)])
  (:require
   [contour.crossover.shared :as shared]
   [hiccup
    [page :refer (html5)]
    [element :refer (javascript-tag)]
    [page :refer (include-js include-css)]]))

(defn- run-clojurescript [path init]
  (list (include-js path)
        (javascript-tag init)))

(defn layout [& content]
  (html5
   [:head
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
    [:meta {:content "text/html;charset=UTF-8"
            :http-equiv "content-type"}]
    [:title "Contour"]
    (include-css "/css/reset.css")]
   [:body content]))

(defn dev-mode? []
  (= "development" (:ring-env env)))

(defn index-page []
  (layout
   (include-js "http://maps.googleapis.com/maps/api/js?sensor=false")
   (if (dev-mode?)
     (run-clojurescript "/js/main-debug.js"
                        "contour.repl.connect()")
     (include-js "/js/main.js"))
   (javascript-tag "goog.require('contour.mapview')")
   [:div#map_canvas]))
