(ns contas-gracie.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager)) ; use import to reference Java classes

(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "resources/db/prod.sq3"})

(defmacro with-db [f & body]
  `(sql/with-connection ~db (~f ~@body)))

(defn create-users-table []
  (with-db sql/create-table
      :users
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:name "TEXT NOT NULL"]
      [:email "TEXT NOT NULL"]
      [:password "TEXT NOT NULL"]
      [:cellphone "INTEGER NOT NULL"]
      [:created "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]))

