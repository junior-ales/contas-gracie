(ns contas-gracie.models.user
  (:require [clojure.java.jdbc :as sql]
            [contas-gracie.models.db :refer [with-db]]))

;; TODO that's the final format of the collection of all users
;;(def users
;;  (atom '({:name "junior" :bills-responsible-for [] :payer? false}
;;          {:name "desiree" :bills-responsible-for ["rent" "electricity"] :payer? true}
;;          {:name "brian" :bills-responsible-for ["internet"] :payer? false})))

(defn get-users []
  (with-db sql/with-query-results
    result ["SELECT * FROM users"] (doall result)))

; No, we're not going to save the password in plain text
(defn insert-user [email password]
  (with-db sql/insert-values
    :users
    [:email :password :timestamp]
    [email password (new java.util.Date)]))

(defn user-exists? [email password]
  (<= 1
      (with-db sql/with-query-results*
        [(str "SELECT * FROM users WHERE email = '" email "' AND password = '" password "'")]
        (fn [x] (count x)))))
