(ns contour.mapview
  (:require [contour.util :as u]
            [goog.events :as events]
            [goog.style :as style]))

(def default-opts
  {:zoom 8
   :mapTypeId google.maps.MapTypeId.ROADMAP
   :center (google.maps.LatLng. -34.397, 150.644)})

(defn init-map [element]
  (let [options (u/clj->js default-opts)]
    (google.maps.Map. element options)))

(def *map* nil)

(defn ^:export mk []
  (set! *map* (init-map
               (goog.dom/getElement
                "map_canvas"))))

;; Sets the whole business in motion.
(events/listen js/window "load" map-load)
