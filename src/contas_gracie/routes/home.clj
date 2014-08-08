(ns contas-gracie.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer [generate-string]]
            [contas-gracie.models.user :refer [all-users find-user insert-user]]
            [contas-gracie.models.bill :refer [all-bills]]
            [liberator.core :refer [defresource resource]]))

(defresource get-user [id]
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (find-user id)))
  :available-media-types ["application/json"])

(defresource get-users
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (all-users)))
  :available-media-types ["application/json"])

(defresource create-user
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :post!
  (fn [ctx]
    (let [params (get-in ctx [:request :form-params])]
      (insert-user
        (get params "name")
        (get params "email")
        (get params "password")
        (get params "cellphone"))))
  :post-redirect? (fn [ctx] {:location "/users"}))

(defresource get-bills
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (all-bills)))
  :available-media-types ["application/json"])

(defroutes home-routes
  (ANY "/users" request get-users)
  (ANY "/users/new" request create-user)
  (ANY "/users/:id" [id] (get-user id))
  (ANY "/bills" request get-bills))
