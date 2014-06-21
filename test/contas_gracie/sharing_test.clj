(ns contas-gracie.sharing-test
  (:require [clojure.test :refer :all]
            [contas-gracie.sharing :refer :all]))

(deftest evenly-shared-amount-test
  (testing "The amount shared evenly among the users"
    (let [bills '({:name "foo" :amount 100})
          users '({:name "x" :bills-responsible-for [] :payer? true}
                  {:name "x" :bills-responsible-for [] :payer? true})]
      (is (= (/ (total-amount bills) (count users)) 50)))))

(deftest grant-amount-test
  (testing "The sum of all bills"
    (let [bills '({:name "foo" :amount 120}
                  {:name "foo" :amount 100})]
      (is (= (total-amount bills) 220)))))

(deftest tranfer-amount-test
  (testing "The amount each user need to transfer to the payer"
    (let [bills '({:name "foo" :amount 100}
                  {:name "bar" :amount 120})
          users '({:name "x" :bills-responsible-for ["foo" "bar"] :payer? true}
                  {:name "y" :bills-responsible-for [] :payer? false})]
      (is (= (amount-to-transfer "x" bills users) 0))
      (is (= (amount-to-transfer "y" bills users) 110)))))

(deftest find-user-test
  (testing "Find user function"
    (let [user-x {:name "x" :bills-responsible-for [] :payer? true}
          user-y {:name "y" :bills-responsible-for [] :payer? false}
          users [user-x user-y]]
      (is (= (find-by-name "x" users) user-x)))))

(deftest amount-to-pay-test
  (testing "The amount to pay"
    (let [bills '({:name "foo" :amount 100})
          users '({:name "x" :bills-responsible-for ["foo"] :payer? true}
                  {:name "y" :bills-responsible-for [] :payer? false})]
      (is (= (amount-to-pay "x" bills users) 100)))))

