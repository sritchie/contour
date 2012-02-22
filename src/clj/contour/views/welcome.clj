(ns contour.views.welcome
  (:use [noir.core :only (defpage)]
        [noir.options :only (dev-mode?)]
        [hiccup.page-helpers :only (include-js javascript-tag)])
  (:require [contour.views.common :as common]))

(defpage "/" []
  (common/layout 
   (include-js "http://maps.googleapis.com/maps/api/js?sensor=false")
   (include-js "/js/main.js")
   (when (dev-mode?)
     (javascript-tag "goog.require('contour.repl');"))
   (javascript-tag "goog.require('contour.mapview')")
   [:div#map_canvas]))
