(ns contour.mapview
  (:require [clojure.string :as s]
            [goog.events :as events]))

(def overlay-defaults
  "default overlay options; we're assuming that the tilesize is
  standard, etc."
  {:minZ 3
   :maxZ 10
   :tileSize (google.maps.Size. 256 256)})

(defn mk-overlay
  "Returns a Google Maps overlay with the supplied name,
  url-generating function and opacity."
  [name-str url-func opacity]
  (let [opts (clj->js
              (merge overlay-defaults
                     {:name name-str
                      :opacity opacity
                      :getTileUrl url-func}))]
    (google.maps.ImageMapType. opts)))

(def map-opts
  "Default initial map options."
  {:zoom 3
   :mapTypeId google.maps.MapTypeId.ROADMAP
   :center (google.maps.LatLng. 29, 66)
   :styles [{:stylers [{:visibility "on"}
                       {:lightness 80}]}]})

(defn init-map
  [element overlays]
  (let [options (clj->js map-opts)
        map (google.maps.Map. element options)
        types (.-overlayMapTypes map)]
    (doseq [layer overlays]
      (.push types layer))
    map))

(def *map*
  "Dynamic variable holding our map element, set to an initial value
   of nil. We don't really need to bind this to anything, but it helps
   to have a reference to it from the callback for later coding."
  nil)

(defn map-load []
  (letfn [(tile-url [coord zoom]
            (str (.-URL js/document)
                 zoom "/" (.-x coord) "/" (.-y coord) ".png"))]
    (set! *map* (init-map
                 (goog.dom/getElement "map_canvas")
                 [(mk-overlay "iucn" tile-url 0.6)]))))

(events/listen js/window "load" map-load)
