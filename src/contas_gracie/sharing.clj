(ns contas-gracie.sharing)

;; TODO fetch users from database
(def users
  '({:name "junior" :responsible-for [] :payer? false}
    {:name "desiree" :responsible-for ["rent" "electricity"] :payer? true}
    {:name "brian" :responsible-for ["internet"] :payer? false}))

;; TODO fetch bills from database
(def bills
  '({:name "electricity" :amount 90}
    {:name "rent" :amount 1900}
    {:name "internet" :amount 185}))

(defn- finder[diff coll]
  (first
    (filter (complement nil?)
            (map (fn[subj]
                   (if (diff subj) subj))
                 coll))))

(defn total-amount[user-bills]
  (apply + (map (fn[bill] (:amount bill)) user-bills)))

(defn payer[]
  (finder :payer? users))

(defn find-user[user-name]
  (finder (fn[user] (= (:name user) user-name)) users))

(defn find-bill[bill-name]
  (finder (fn[bill] (= (:name bill) bill-name)) bills))

;; Amount to pay to companies (e.g. CEEE, NET)
(defn amount-to-pay[user-name]
  (total-amount (map (fn[bill-name] (find-bill bill-name))
                     (:responsible-for (find-user user-name)))))

;; Amount to transfer to the payer user
(defn amount-to-transfer[user-name]
  (let [user (find-user user-name)
        grant-total (total-amount bills)
        evenly-shared-amount (/ grant-total (count users))]
    (if (= user (payer))
      0
      (if (empty? (:responsible-for user))
        evenly-shared-amount
        (- evenly-shared-amount (amount-to-pay (:name user)))))))
