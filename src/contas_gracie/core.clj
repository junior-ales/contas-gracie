(ns contas-gracie.core
  (:use compojure.core)
  (:use contas-gracie.db)
  (:use contas-gracie.sharing)
  (:use ring.middleware.params)
  (:require
    [ring.adapter.jetty :as jetty]
    [net.cgrand.enlive-html :as enlive]
    [compojure.route :as route]))

;; Template Rendering
(defn read-partial [partial-name]
  (slurp (clojure.java.io/resource
           (str "templates/partials/" partial-name ".html"))))

(defn has-monthly-bills? [] false) ;; it will check in the DB if there's bills for the current month

(defn monthly-bills-button []
  (if (has-monthly-bills?)
    (enlive/do->
      (enlive/add-class "alert")
      (enlive/set-attr :href "/monthly-bills/current")
      (enlive/content "Visualizar Contas do Mês"))
    identity))

(enlive/defsnippet header "templates/_header.html" [:section#main-header] []
  [:a#monthly-bills] (monthly-bills-button))

(enlive/deftemplate main-layout "templates/layout.html" [title content]
  [:title] (enlive/content title)
  [:section#content] (enlive/html-content content))

(enlive/deftemplate authorized-layout "templates/layout.html" [title content]
  [:title] (enlive/content title)
  [:header#main] (enlive/content (header))
  [:section#content] (enlive/html-content content))

;; View functions
(defn login []
  (apply str (main-layout "Contas Gracie" (read-partial "login"))))

(defn home []
  (apply str (authorized-layout "Contas Gracie - Bem-vindo" (read-partial "profile"))))

(defn authenticate [email password]
  (if (user-exists? email password)
    (apply str (authorized-layout "Contas Gracie - Bem-vindo" (read-partial "profile")))
    (apply str (authorized-layout "Contas Gracie - Authentication faile" (read-partial "not-authorized")))))

;; Routing
(defroutes main-routes
  (GET "/" [] (home))
  (GET "/login" [] (login))
  (POST "/login" [email password] (authenticate email password))

  (route/resources "/")
  (route/resources "/login")
  (route/not-found "404 Not Found"))

(def app (wrap-params main-routes))

;; Server
(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty app {:port port})))
