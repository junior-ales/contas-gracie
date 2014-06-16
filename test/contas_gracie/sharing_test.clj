;; Those tests are wrong in so many ways that I'm embarassed
;; to commit them. But they truly helped me develop it and
;; I'm sure they can be refactored and then deliver good value

(ns contas-gracie.sharing-test
  (:require [clojure.test :refer :all]
            [contas-gracie.sharing :refer :all]))

;; TODO find a way to override "users" and "bills" values so it can be properly tested

(deftest evenly-shared-amount-test
  (testing "The amount shared evenly among the users"
    (is (= (/ (total-amount bills) (count users)) 725))))

(deftest grant-amount-test
  (testing "The sum of all bills"
    (is (= (total-amount bills) 2175))))

(deftest tranfer-amount-test
  (testing "The amount each user need to transfer to the payer"
    (is (= (amount-to-transfer "desiree") 0))
    (is (= (amount-to-transfer "brian") 540))
    (is (= (amount-to-transfer "junior") 725))))

