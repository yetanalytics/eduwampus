(ns eduwampus.styler
  (:require [garden.core :refer [css]]))


(defn rand-img []
  (str (+ 1 (rand-int 17)) ".jpg"))

(defn rand-body []
  (css [:.corp-identity
      {:background-image (str "url('/" (rand-img) "')")
       :background-opacity 0.4}]))

