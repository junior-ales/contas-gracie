(ns contas-gracie.models.user
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [contas-gracie.models.db :refer [db-connection]]))

(defdb db db-connection)
(defentity users)

(defn all-users []
  (select users))

(defn find-user [id]
  (select users
          (where {:id id})))

(defn insert-user [name email password cellphone]
  (insert users
          (values {:name name
                   :email email
                   :password password
                   :cellphone cellphone})))
;; No, we're not going to save the password in plain text
;(defn insert-user [name email password cellphone]
;  (with-db sql/insert-values
;    :users
;    [:name :email :password :cellphone]
;    [name email password cellphone]))
;
;(defn user-exists? [email password]
;  (<= 1
;      (with-db sql/with-query-results*
;        [(str "SELECT * FROM users WHERE email = '" email "' AND password = '" password "'")]
;        (fn [x] (count x)))))

;; TODO that's the final format of the collection of all users
;;(def users
;;  (atom '({:name "junior" :bills-responsible-for [] :payer? false}
;;          {:name "desiree" :bills-responsible-for ["rent" "electricity"] :payer? true}
;;          {:name "brian" :bills-responsible-for ["internet"] :payer? false})))
