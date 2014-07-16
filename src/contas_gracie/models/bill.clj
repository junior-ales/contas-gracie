(ns contas-gracie.models.bill)

;; TODO fetch bills from database
(def bills
  (atom '({:name "electricity" :amount 90}
          {:name "rent" :amount 1900}
          {:name "internet" :amount 185})))

