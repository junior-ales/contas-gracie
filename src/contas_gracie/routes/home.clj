(ns contas-gracie.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer [generate-string]]
            [contas-gracie.models.user :refer :all]
            [liberator.core
             :refer [defresource resource request-method-in]]))

(defresource users
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (get-users)))
  :available-media-types ["application/json"])

(defroutes home-routes
  (ANY "/users" request users))
