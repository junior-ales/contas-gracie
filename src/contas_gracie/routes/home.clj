(ns contas-gracie.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer [generate-string]]
            [contas-gracie.models.user :refer [all-users find-user]]
            [liberator.core :refer [defresource resource request-method-in]]))

(defresource get-user [id]
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (find-user id)))
  :available-media-types ["application/json"])

(defresource get-users
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (all-users)))
  :available-media-types ["application/json"])

(defroutes home-routes
  (GET "/users" request get-users)
  (GET "/users/:id" [id] (get-user id)))
