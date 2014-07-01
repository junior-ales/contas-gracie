(ns contas-gracie.sharing-test
  (:require [clojure.test :refer :all]
            [contas-gracie.sharing :refer :all]))

(deftest evenly-shared-amount-test
  (testing "The amount shared evenly among the users"
    (let [bills '({:name "bill-1" :amount 100})
          users '({:name "user-1" :bills-responsible-for [] :payer? true}
                  {:name "user-1" :bills-responsible-for [] :payer? true})]
      (is (= (evenly-shared bills users) 50)))))

(deftest grant-amount-test
  (testing "The sum of all bills"
    (let [bills '({:name "bill-1" :amount 120}
                  {:name "bill-1" :amount 100})]
      (is (= (total-amount bills) 220)))))

(deftest tranfer-amount-test
  (testing "The amount each user need to transfer to the payer"
    (let [bills '({:name "bill-1" :amount 60}
                  {:name "bill-2" :amount 150})
          users '({:name "user-1" :bills-responsible-for ["bill-2"] :payer? true}
                  {:name "user-2" :bills-responsible-for [] :payer? false}
                  {:name "user-3" :bills-responsible-for ["bill-1" ] :payer? false})]
      (is (= (amount-to-transfer "user-1" bills users) 0))
      (is (= (amount-to-transfer "user-2" bills users) 70))
      (is (= (amount-to-transfer "user-3" bills users) 10)))))

(deftest find-user-test
  (testing "Find user function"
    (let [user-x {:name "user-1" :bills-responsible-for [] :payer? true}
          user-y {:name "user-2" :bills-responsible-for [] :payer? false}
          users [user-x user-y]]
      (is (= (find-by-name "user-1" users) user-x)))))

(deftest amount-to-pay-test
  (testing "The amount to pay"
    (let [bills '({:name "bill-1" :amount 100})
          users '({:name "user-1" :bills-responsible-for ["bill-1"] :payer? true}
                  {:name "user-2" :bills-responsible-for [] :payer? false})]
      (is (= (amount-to-pay "user-1" bills users) 100)))))

(deftest user-summary-test
  (testing "The summary of a given user when the payer pays all bills"
    (let [bills '({:name "bill-1" :amount 100}
                  {:name "bill-2" :amount 120})
          users '({:name "user-1" :bills-responsible-for ["bill-1" "bill-2"] :payer? true}
                  {:name "user-2" :bills-responsible-for [] :payer? false})
          expected-summary-1 {:name "user-1" :pays 220 :transfer 0 :transfer-to nil}
          expected-summary-2 {:name "user-2" :pays 0 :transfer 110 :transfer-to "user-1"}]
      (is (= (user-summary "user-1" bills users) expected-summary-1))
      (is (= (user-summary "user-2" bills users) expected-summary-2))))
  (testing "The summary of a given user when the payer doesn't pay all the bills"
    (let [bills '({:name "bill-1" :amount 100}
                  {:name "bill-2" :amount 120}
                  {:name "bill-3" :amount 140})
          users '({:name "user-1" :bills-responsible-for ["bill-1" "bill-2"] :payer? true}
                  {:name "user-2" :bills-responsible-for ["bill-3"] :payer? false})
          expected-summary-1 {:name "user-1" :pays 220 :transfer 0 :transfer-to nil}
          expected-summary-2 {:name "user-2" :pays 140 :transfer 40 :transfer-to "user-1"}]
      (is (= (user-summary "user-1" bills users) expected-summary-1))
      (is (= (user-summary "user-2" bills users) expected-summary-2)))))


(deftest summary-test
  (testing "The summary of payments"
    (let [bills '({:name "bill-1" :amount 100}
                  {:name "bill-2" :amount 120}
                  {:name "bill-3" :amount 140})
          users '({:name "user-1" :bills-responsible-for ["bill-1" "bill-2"] :payer? true}
                  {:name "user-2" :bills-responsible-for ["bill-3"] :payer? false})
          expected-summary [{:name "user-1" :pays 220 :transfer 0 :transfer-to nil}
                            {:name "user-2" :pays 140 :transfer 40 :transfer-to "user-1"}]]
      (is (= (summary bills users) expected-summary)))))

