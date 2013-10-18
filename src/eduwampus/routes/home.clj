(ns eduwampus.routes.home
  (:use compojure.core)
  (:require [eduwampus.views.layout :as layout]
            [eduwampus.util :as util]
            [noir.response :as response]
            [edufaker.company :as fake]
            [eduwampus.styler :refer [rand-body]]))

(declare generate-startup)

(defn home-page []
  (layout/render
    "home.html"  (generate-startup)))

(defn get-startup []
  (response/edn
   (generate-startup)))

(defn about-page []
  (layout/render "about.html"))

(defn generate-startup []
  {:cname (fake/startup) :catchphrase (str (fake/catch-phrase) " to " (fake/edu-bs)) :styles (rand-body)})



(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/startup" [] (get-startup)))
