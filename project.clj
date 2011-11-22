(defproject contour "0.1.0"
  :description "Example Clojurescript project for Google Maps."
  :source-path "src/clj"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [noir "1.2.1"]
                 [pinot "0.1.1-SNAPSHOT"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [lein-ring "0.4.6"]]
  :ring {:handler contour.server/handler}
  :main contour.server)
