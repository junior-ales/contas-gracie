(ns contas-gracie.routes.home
  (:require [compojure.core :refer :all]))

(defroutes home-routes
  (GET "/" [] (str "Hello World!")))
