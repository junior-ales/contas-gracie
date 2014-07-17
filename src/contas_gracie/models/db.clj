(ns contas-gracie.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager)) ; use import to reference Java classes

(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "db.sq3"})

(defmacro with-db [f & body]
  `(sql/with-connection ~db (~f ~@body)))

(defn create-users-table []
  (with-db sql/create-table
      :users
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:email "TEXT"]
      [:password "TEXT"]))

