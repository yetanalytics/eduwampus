(ns eduwampus.main
  (:require [ajax.core :refer [GET POST]]
            [domina :refer [value by-id destroy-children! append!]]
            [domina.events :refer [listen!]]
            [domina.css :as jq]
            [dommy.template :as template]))

;;(defn handler [response]
;;  (.log js/console (str response)))

;;(defn error-handler [{:keys [status status-text]}]
;;  (.log js/console
;;        (str "something bad " status " " status-text)))

;;(GET "/hello")

;;(GET "/hello" {:handler handler
;;               :error-handler error-handler})

(defn render-company [{:keys [cname catchphrase styles]}]
  [:div [:h1 cname] [:h3 catchphrase]]
  (append! "head" (str "<style type='text/css'>" styles "</style>")))

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
  ;(GET "/startup" {:handler render-company})
  (listen! (by-id "generate") :click get-startup))
