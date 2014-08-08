(ns contas-gracie.models.bill
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [contas-gracie.models.db :refer [db-connection]]))

(defdb db db-connection)
(defentity bills)

(defn all-bills []
  (select bills))

;; TODO fetch bills from database
;;(def bills
;;  (atom '({:name "electricity" :amount 90}
;;          {:name "rent" :amount 1900}
;;          {:name "internet" :amount 185})))

