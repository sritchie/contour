(defproject contour "0.1.0"
  :description "Example Clojurescript project for Google Maps."
  :repositories {"sonatype-oss"
                 "http://oss.sonatype.org/content/groups/public/"}
  :source-path "src/clj"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojurescript "0.0-971"]
                 [noir "1.2.2"]
                 [pinot "0.1.1-SNAPSHOT"]]
  :dev-dependencies [[lein-ring "0.5.0"]]
  :plugins [[lein-cljsbuild "0.0.14"]]
  :cljsbuild {:source-path "src/cljs"
              :compiler {:output-to "resources/public/js/main.js"
                         :optimizations :whitespace
                         :pretty-print true}}
  :ring {:handler contour.server/handler}
  :main contour.server)
