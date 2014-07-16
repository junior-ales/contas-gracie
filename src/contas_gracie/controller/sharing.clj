(ns contas-gracie.controller.sharing)

(def finder (comp first filter))

(defn total-amount[user-bills]
  (apply + (map (fn[bill] (:amount bill)) user-bills)))

(defn evenly-shared[bills users]
  (/ (total-amount bills) (count users)))

(defn payer[users]
  (finder :payer? users))

(defn find-by-name[a-name coll]
  (finder #(= (:name %) a-name) coll))

;; Amount to pay to companies (e.g. CEEE, NET)
(defn amount-to-pay[user-name bills users]
  (total-amount (map (fn[bill-name] (find-by-name bill-name bills))
                     (:bills-responsible-for (find-by-name user-name users)))))

;; Amount to transfer to the payer user
(defn amount-to-transfer[user-name bills users]
  (let [user (find-by-name user-name users)]
    (if (= user (payer users))
      0
      (if (empty? (:bills-responsible-for user))
        (evenly-shared bills users)
        (- (evenly-shared bills users) (amount-to-pay user-name bills users))))))

(defn user-summary[user-name bills users]
  (let [payer-name (:name (payer users))]
  {:name user-name
   :pays (amount-to-pay user-name bills users)
   :transfer (amount-to-transfer user-name bills users)
   :transfer-to (if (not= payer-name user-name) payer-name)}))

(defn summary[bills users]
  (let [user-names (map #(:name %) users)]
    (map (fn[user-name]
          (user-summary user-name bills users)) user-names)))
