(ns contour.crossover.shared
  (:require;*CLJSBUILD-REMOVE*;-macros
   [contour.crossover.macros :as macros]))

(defn make-example-text []
  (macros/reverse-eval
    ("code" "shared " "from the " "Hello " str)))
