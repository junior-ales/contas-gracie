(ns contas-gracie.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer [generate-string]]
            [contas-gracie.models.user :refer [users]]
            [liberator.core
             :refer [defresource resource request-method-in]]))

(defresource get-users
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string users))
  :available-media-types ["application/json"])

(defroutes home-routes
  (ANY "/users" request get-users))
