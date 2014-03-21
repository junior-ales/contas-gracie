(ns contas-gracie.core
  (:use compojure.core)
  (:require
    [ring.adapter.jetty :as jetty]
    [net.cgrand.enlive-html :as enlive]
    [compojure.route :as route]))

;; Template Rendering
(defn read-partial [partial-name]
  (slurp (clojure.java.io/resource
    (str "templates/partials/" partial-name ".html"))))

(enlive/defsnippet header "templates/_header.html" [:section#main-header] [] identity)

(enlive/deftemplate main-layout "templates/layout.html" [title content]
  [:title] (enlive/content title)
  [:section#content] (enlive/html-content content))

(enlive/deftemplate authorized-layout "templates/layout.html" [title content]
  [:title] (enlive/content title)
  [:header#main] (enlive/content (header))
  [:section#content] (enlive/html-content content))

;; View functions
(defn index []
    (apply str (main-layout "Contas Gracie" (read-partial "login"))))

(defn inicio []
    (apply str (authorized-layout "Contas Gracie - Benvindo" (read-partial "profile"))))

;; Routing
(defroutes main-routes
  (GET "/" [] (index))
  (GET "/inicio" [] (inicio))
  (route/resources "/")
  (route/resources "/inicio")
  (route/not-found "404 Not Found"))

;; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-routes {:port port})))
