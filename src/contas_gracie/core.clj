(ns contas-gracie.core
  (:use compojure.core)
  (:require
    [ring.adapter.jetty :as jetty]
    [net.cgrand.enlive-html :as enlive]
    [compojure.route :as route]))

;; Template Rendering
(defn read-template [template-name]
  (slurp (clojure.java.io/resource
    (str "templates/partials/" template-name ".html"))))

(enlive/deftemplate main-layout "templates/layout.html" [title content]
  [:title] (enlive/content title)
  [:section#main] (enlive/html-content content))

;; View functions
(defn index []
    (apply str (main-layout "Contas Gracie" (read-template "login"))))

;; Routing
(defroutes main-routes
  (GET "/" [] (index))
  (route/resources "/")
  (route/not-found "404 Not Found"))

;; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-routes {:port port})))
