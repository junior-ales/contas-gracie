(ns contas-gracie-cli.sharing)

;; DEFINITELY it will change
(def users
  '({:name "junior" :responsible-for [] :payer? false}
    {:name "desiree" :responsible-for ["electricity" "rent"] :payer? true}
    {:name "brian" :responsible-for ["internet"] :payer? false}))

;; DEFINITELY it will change
(def bills
  '({:name "electricity" :amount 30}
    {:name "rent" :amount 50}
    {:name "internet" :amount 40}))

(defn- finder[diff coll]
  (first
    (filter (complement nil?)
            (map (fn[subj]
                   (if (diff subj) subj))
                 coll))))

(defn- total-amount[user-bills]
  (apply + (map (fn[bill] (:amount bill)) user-bills)))

;;(defn payer[]
;;  (finder :payer? users))

(defn- find-user[user-name]
  (finder (fn[user] (= (:name user) user-name)) users))

(defn- find-bill[bill-name]
  (finder (fn[bill] (= (:name bill) bill-name)) bills))

; amount to pay to companies (e.g. CEEE, NET)
(defn amount-to-pay[user-name]
  (total-amount (map (fn[bill-name] (find-bill bill-name))
                     (:responsible-for (find-user user-name)))))

;; TODO create a method 'amount-to-transfer' so payment to companies and to users are differentiated
