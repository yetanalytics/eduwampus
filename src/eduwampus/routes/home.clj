(ns eduwampus.routes.home
  (:use compojure.core)
  (:require [eduwampus.views.layout :as layout]
            [eduwampus.util :as util]
            [edufaker.company :as fake]))

(declare generate-startup)

(defn home-page []
  (layout/render
    "home.html"  (generate-startup)))

(defn about-page []
  (layout/render "about.html"))

(defn generate-startup []
  {:name (fake/startup) :catchphrase (fake/catch-phrase)})

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))
