; Keep in mind that I have no idea what I'm doing.

(ns contas-gracie.db
  (:require [clojure.java.jdbc :as sql]) ; use require to reference other clojure namespaces
  (:import java.sql.DriverManager)) ; use import to reference Java classes

(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "db.sq3"})

(defn create-users-table []
  ; sql/with-connection ensures that the connection is properly cleaned up after use
  (sql/with-connection
    db
    (sql/create-table
      :users
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:email "TEXT"]
      [:password "TEXT"])))

; No, we're not going to save the password in plain text
(defn insert-user [email password]
  (sql/with-connection
    db
    (sql/insert-values
      :users
      [:email :password :timestamp]
      [email password (new java.util.Date)])))

(defn user-exists? [email password]
  (<= 1 (sql/with-connection
    db
    (sql/with-query-results*
      [(str "SELECT * FROM users where email = '" email "' and password = '" password "'")]
      (fn [x] (count x))))))
