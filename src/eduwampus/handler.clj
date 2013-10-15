(ns eduwampus.handler
  (:require [compojure.core :refer [defroutes]]
            [eduwampus.routes.home :refer [home-routes]]
            [noir.util.middleware :as middleware]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [com.postspectacular.rotor :as rotor]
            [eduwampus.routes.cljsexample :refer [cljs-routes]]))

(defroutes
  app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (timbre/set-config!
    [:appenders :rotor]
    {:min-level :info,
     :enabled? true,
     :async? false,
     :max-message-per-msecs nil,
     :fn rotor/append})
  (timbre/set-config!
    [:shared-appender-config :rotor]
    {:path "eduwampus.log", :max-size (* 512 1024), :backlog 10})
  (timbre/info "eduwampus started successfully"))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (timbre/info "eduwampus is shutting down..."))

(def app
 (middleware/app-handler
   [cljs-routes home-routes app-routes]
   :middleware
   []
   :access-rules
   []
   :formats
   [:json-kw :edn]))

(def war-handler (middleware/war-handler app))

