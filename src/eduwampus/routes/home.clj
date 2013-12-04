(ns eduwampus.routes.home
  (:use compojure.core)
  (:require [eduwampus.views.layout :as layout]
            [eduwampus.util :as util]
            [noir.response :as response]
            [edufaker.company :as fake]
            [edufaker.seed :as seedy]
            [anjel.core :as anjel]
            [eduwampus.styler :refer [rand-body]]))

(declare generate-startup)
(declare generate-startup-seedy)
(declare generate-seed)


(defn home-page []
  (layout/render
    "home.html"))

(defn get-startup [seed]
  (response/json
   (generate-startup-seedy seed)))

(defn angel-search [startup]
  (if-let [result (anjel/find-startup startup)]
    (response/json result)
    (response/status 422 nil)))

(defn about-page []
  (layout/render "about.html"))

(defn generate-startup []
  {:cname (fake/startup) :catchphrase (str (fake/catch-phrase) " to " (fake/edu-bs)) :styles (rand-body)})

(defn generate-startup-seedy [seed]
  (seedy/with-seed generate-startup seed)
  )

(defn generate-seed []
  (read-string
    (str 30000)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/angel-search" [startup] (angel-search startup))
  (GET "/startup/:seed" [seed] (get-startup (read-string seed))))

