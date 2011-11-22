(ns contour.server
  (:require [noir.server :as server]))

(server/load-views "src/clj/contour/views/")

(defn mk-opts [mode]
  {:mode (keyword (or mode :dev))
   :ns 'contour})

(def handler
  "Handler for the Contour server."
  (server/gen-handler (mk-opts :dev)))

(defn -main
  "Main entry point."
  [& [mode :as args]]
  (let [port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port (mk-opts mode))))

