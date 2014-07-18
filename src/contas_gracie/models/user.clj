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

(def users (get-users))

(defn find-user [id]
   (with-db sql/with-query-results
     result ["SELECT * FROM users WHERE id = ?" id]
     (first result)))

; No, we're not going to save the password in plain text
(defn insert-user [name email password cellphone]
  (with-db sql/insert-values
    :users
    [:name :email :password :cellphone]
    [name email password cellphone]))

(defn user-exists? [email password]
  (<= 1
      (with-db sql/with-query-results*
        [(str "SELECT * FROM users WHERE email = '" email "' AND password = '" password "'")]
        (fn [x] (count x)))))
