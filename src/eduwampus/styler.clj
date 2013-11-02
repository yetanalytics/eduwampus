(ns eduwampus.styler
  (:require [garden.core :refer [css]]
            [clojure.data.generators :as gen]))


(defn rand-img []
  (str (+ 1 (gen/rand-nth (range 17))) ".jpg"))

(defn rand-body []
  {:background (str "url('/img/" (rand-img) "') no-repeat center center fixed")
   :backgroundOpacity "0.4"
   :backgroundSize "cover"})
