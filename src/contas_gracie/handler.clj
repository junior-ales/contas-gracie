(ns contas-gracie.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [contas-gracie.routes.home :refer [home-routes]]
            [contas-gracie.routes.user :refer [user-routes]]
            [contas-gracie.routes.bill :refer [bill-routes]]))

(defn init []
  (println "contas-gracie is starting"))

(defn destroy []
  (println "contas-gracie is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes
        home-routes
        user-routes
        bill-routes
        app-routes)
      (handler/site)
      (wrap-base-url)))


