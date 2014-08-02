(ns contas-gracie.models.db)

(def db-connection {:classname "org.sqlite.JDBC",
                    :subprotocol "sqlite",
                    :subname "resources/db/prod.sq3"})

