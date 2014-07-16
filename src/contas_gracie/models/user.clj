(ns contas-gracie.models.user)

;; TODO fetch users from database
(def users
  (atom '({:name "junior" :bills-responsible-for [] :payer? false}
          {:name "desiree" :bills-responsible-for ["rent" "electricity"] :payer? true}
          {:name "brian" :bills-responsible-for ["internet"] :payer? false})))

