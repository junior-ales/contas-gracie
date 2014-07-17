(ns contas-gracie.models.user
  (:require [clojure.java.jdbc :as sql]
            [contas-gracie.models.db :refer [db]]))

;; TODO fetch users from database
(def users
  (atom '({:name "junior" :bills-responsible-for [] :payer? false}
          {:name "desiree" :bills-responsible-for ["rent" "electricity"] :payer? true}
          {:name "brian" :bills-responsible-for ["internet"] :payer? false})))

; No, we're not going to save the password in plain text
(defn insert-user [email password]
  (sql/with-connection db
    (sql/insert-values
      :users
      [:email :password :timestamp]
      [email password (new java.util.Date)])))

(defn user-exists? [email password]
  (<= 1 (sql/with-connection db
    (sql/with-query-results*
      [(str "SELECT * FROM users WHERE email = '" email "' AND password = '" password "'")]
      (fn [x] (count x))))))
