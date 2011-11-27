(ns contour.views.common
  (:use [noir.core :only (defpartial)]
        [hiccup.page-helpers :only (include-css html5)]))

(defpartial layout [& content]
  (html5
   [:head
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0, user-scalable=no"}]
    [:meta {:content "text/html;charset=UTF-8"
            :http-equiv "content-type"}]
    [:title "Contour"]
    (include-css "/css/reset.css")]
   [:body content]))
