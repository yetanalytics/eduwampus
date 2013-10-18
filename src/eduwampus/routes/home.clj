(ns eduwampus.routes.home
  (:use compojure.core)
  (:require [eduwampus.views.layout :as layout]
            [eduwampus.util :as util]
            [edufaker.company :as fake]))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

(defn generate-startup []
  {:name (fake/startup) :catchphrase (fake/catch-phrase)})

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))

(generate-startup)
