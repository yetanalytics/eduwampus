(ns eduwampus.main
  (:require [ajax.core :refer [GET POST]]
            [domina :refer [value by-id destroy-children! append!]]
            [domina.events :refer [listen!]]
            [dommy.template :as template]))

(defn render-message [{:keys [message user]}]
  [:li [:p {:id user} message " - " user]])

(defn render-messages [messages]
  (let [messages-div (by-id "messages")]
    (destroy-children! messages-div)
    (->> messages
         (map render-message)
         (into [:ul])
         template/node
         (append! messages-div))))

(defn render-company [{:keys [cname catchphrase styles]}]
  [:div [:h1 cname] [:h3 catchphrase]])

(defn generate-startup [company]
  (let [company-div (by-id "company")]
    (destroy-children! company-div)
    (->> company
         (map render-message)
         (into [:p])
         template/node
         (append! company-div))))

(defn add-message [_]
  (POST "/add-message"
        {:params {:message (value (by-id "message"))
                  :user    (value (by-id "user"))}
         :handler render-messages}))

(defn get-startup [_]
  (GET "/startup" {:handler render-company}))

(defn ^:export init []
  (GET "/startup" {:handler render-company})
  (listen! (by-id "generate") :click add-message))
