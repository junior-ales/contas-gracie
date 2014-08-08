(ns contas-gracie.routes.bill
  (:require [compojure.core :refer :all]
            [cheshire.core :refer [generate-string]]
            [contas-gracie.models.bill :refer [all-bills]]
            [liberator.core :refer [defresource resource]]))

(defresource get-bills
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (all-bills)))
  :available-media-types ["application/json"])

(defroutes bill-routes
  (ANY "/bills" request get-bills))
