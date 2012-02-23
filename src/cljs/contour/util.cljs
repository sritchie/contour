(ns contour.util
  (:require [clojure.string :as s]))

;; Thanks to mmcgrana:
;; http://mmcgrana.github.com/2011/09/clojurescript-nodejs.html
;; Updated from:
;; http://techylinguist.com/posts/2012/01/23/clojurescript-getting-started
(defn clj->js
  "Recursively transforms ClojureScript maps into Javascript objects,
   other ClojureScript colls into JavaScript arrays, and ClojureScript
   keywords into JavaScript strings.

   Borrowed and updated from mmcgrana."
  [x]
  (cond (string? x) x
        (keyword? x) (name x)
        (map? x) (.-strobj (reduce (fn [m [k v]]
                                     (assoc m (clj->js k) (clj->js v))) {} x))
        (coll? x) (apply array (map clj->js x))
        :else x))

(defn pathify [& pieces]
  (s/join "/" pieces))
