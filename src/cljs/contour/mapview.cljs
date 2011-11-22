(ns contour.mapview
  (:require [contour.util :as u]
            [goog.events :as events]
            [goog.style :as style]))

(def iucn-root "http://184.73.201.235/blue/")
(def forma-root "http://formatiles.s3.amazonaws.com/tiles/forma")
(def species-range-root "https://eighty.cartodb.com/tiles/mol_cody/")
(def cell-towers-root "https://sciencehackday-10.cartodb.com/tiles/tower_locations/")


(defn cell-towers-tile-url [coord zoom]
    (str cell-towers-root
       zoom "/"
       (.x coord) "/"
       (.y coord) ".png"))

(defn species-range-tile-url [coord zoom]
    (str species-range-root
       zoom "/"
       (.x coord) "/"
       (.y coord) ".png"))

(defn forma-tile-url [coord zoom]
  (let [bound (Math/pow 2 zoom)]
    (str forma-root
         zoom "/"
         (Math/abs (.x coord)) "/"
         (- bound (.y coord) 1) ".png")))

(defn iucn-tile-url [coord zoom]
  (str iucn-root
       zoom "/"
       (.x coord) "/"
       (.y coord)))

(def overlay-defaults
  {:minZ 3
   :maxZ 10
   :tileSize (google.maps.Size. 256 256)})

(defn mk-overlay
  [name-str url-func opacity]
  (let [opts (u/clj->js
              (merge overlay-defaults
                     {:name name-str
                      :opacity opacity
                      :getTileUrl url-func}))]
    (google.maps.ImageMapType. opts)))

;; Default initial map options.
(def map-opts
  {:zoom 8
   :mapTypeId google.maps.MapTypeId.ROADMAP
   :center (google.maps.LatLng. -34.397, 150.644)})

(defn init-map [element]
  (let [options (u/clj->js map-opts)
        map (google.maps.Map. element options)]
    (doto (.overlayMapTypes map)
      (. (insertAt 2 (mk-overlay "iucn" iucn-tile-url 0.5)))
      (. (insertAt 1 (mk-overlay "forma" forma-tile-url 1)))
      (. (insertAt 3 (mk-overlay "cell-towers" cell-towers-tile-url 1)))
      (. (insertAt 0 (mk-overlay "species-range" species-range-tile-url 1))))
    map))

;; We don't really need to bind this to anything, but it helps to have
;; a reference to it from the callback for later coding.
(def *map* nil)

(defn map-load []
  (set! *map* (init-map
               (goog.dom/getElement "map_canvas"))))

;; Callback!
(events/listen js/window "load"
               map-load)
