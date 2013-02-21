(defproject com.twitter/contour "0.1.0-SNAPSHOT"
  :description "Example Clojurescript project for Google Maps."
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.0-RC4"]
                 [environ "0.3.0"]
                 [compojure "1.1.5"]
                 [clj-http "0.6.4"]
                 [hiccup "1.0.2"]]
  :plugins [[lein-ring "0.8.2"]
            [lein-cljsbuild "0.3.0"]
            [lein-midje "3.0-alpha4"]]
  ;; :hooks [leiningen.cljsbuild]
  :cljsbuild
  {:repl-listen-port 9000
   :repl-launch-commands
   {"phantom" ["phantomjs"
               "phantom/repl.js"
               :stdout ".repl-phantom-out"
               :stderr ".repl-phantom-err"]}
   :crossovers [contour.crossover]
   :crossover-jar true
   :builds
   {:dev
    {:source-paths ["src/cljs"]
     :jar true
     :compiler {:output-to "resources/public/js/main-debug.js"
                :optimizations :whitespace
                :pretty-print true}}
    ;; This build has the highest level of optimizations, so it is
    ;; efficient when running the app in production.
    :prod
    {:source-paths ["src/cljs"]
     :compiler {:output-to "resources/public/js/main.js"
                :optimizations :advanced
                :pretty-print false}}}}
  :ring {:handler contour.routes/app})
