(ns contour.mapview
  (:require [contour.util :as u]
            [goog.events :as events]
            [goog.style :as style]))

(def iucn-root "http://184.73.201.235/blue")
(def species-range-root "https://eighty.cartodb.com/tiles/mol_cody")
(def cell-towers-root "https://sciencehackday-10.cartodb.com/tiles/tower_locations")

;; The forma root is odd. the suffix is repeated twice, for
;; one. The "071" is the time period of the particular tileset. We'll
;; make this dynamic next!
(def forma-root "http://formatiles.s3.amazonaws.com/tiles/forma071/forma071")


(defn carto-tiler-fn
  "Takes a root path (without trailing slash) and returns a function
  meant for the :getTileUrl option in a Google map overlay.

  The -> is called the threading macro; it actually rearranges its
  code forms before they hit the compiler. See here for examples:

  http://clojuredocs.org/clojure_core/clojure.core/-%3E"
  [root]
  {:pre [(not= "/" (last root))]} ;; contracts ftw!
  (fn [coord zoom]
    (-> (u/pathify root zoom (.x coord) (.y coord))
        (str ".png"))))

(def cell-towers-tile-url
  (carto-tiler-fn cell-towers-root))

(def species-range-tile-url
  (carto-tiler-fn species-range-root))

(defn iucn-tile-url
  "IUCN tiles don't use a .png extension, for whatever reason."
  [coord zoom]
  (u/pathify iucn-root zoom (.x coord) (.y coord)))

(defn forma-tile-url
  "Wacky shit with inversion of the y coordinate, etc"
  [coord zoom]
  (let [bound (dec (Math/pow 2 zoom))]
    (-> (u/pathify forma-root
                   zoom
                   (Math/abs (.x coord))
                   (- bound (.y coord)))
        (str ".png"))))

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
  (let [opts (u/clj->js
              (merge overlay-defaults
                     {:name name-str
                      :opacity opacity
                      :getTileUrl url-func}))]
    (google.maps.ImageMapType. opts)))

(def map-opts
  "Default initial map options."
  {:zoom 8
   :mapTypeId google.maps.MapTypeId.ROADMAP
   :center (google.maps.LatLng. -34.397, 150.644)})

(defn init-map  [element overlays]
  (let [options (u/clj->js map-opts)
        map (google.maps.Map. element options)]
    (doseq [layer overlays]
      (. (.overlayMapTypes map) (push layer)))
    map))


(def *map*
  "Dynamic variable holding our map element, set to an initial value
   of nil. We don't really need to bind this to anything, but it helps
   to have a reference to it from the callback for later coding."
  nil)

(defn map-load []
  (set! *map* (init-map
               (goog.dom/getElement "map_canvas")
               [(mk-overlay "species-range" species-range-tile-url 1)
                (mk-overlay "forma" forma-tile-url 1)
                (mk-overlay "iucn" iucn-tile-url 0.5)
                (mk-overlay "cell-towers" cell-towers-tile-url 1)])))

;; Callback!
(events/listen js/window "load"
               map-load)
