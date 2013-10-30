(ns eduwampus.styler
  (:require [garden.core :refer [css]]
            [clojure.data.generators :as gen]))


(defn rand-img []
  (str (+ 1 (gen/rand-nth (range 17))) ".jpg"))

(defn rand-body []
  (css [:.corp-identity
      {:background-image (str "url('/" (rand-img) "')")
       :background-opacity 0.4}]))

