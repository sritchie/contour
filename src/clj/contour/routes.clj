(ns contour.routes
  (:use compojure.core
        [hiccup.middleware :only (wrap-base-url)])
  (:require [clojure.string :as s]
            [clj-http.client :as client]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [contour.views :as views]))

(def iucn-root "http://184.73.201.235/blue")

(defn get-tile [zoom x y]
  (->> [iucn-root zoom x y]
       (s/join "/")
       (client/get)
       (:body)
       ))

;; TODO: Generate an image dynamically and serve that shit up.
(defroutes main-routes
  (GET "/" [] (views/index-page))
  (GET "/:zoom/:x/:y.png" [zoom x y]
       (get-tile zoom x y))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (handler/site main-routes))
