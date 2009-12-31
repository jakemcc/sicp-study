; Exercise 3.3

(use 'clojure.test)

(defn make-account [starting-balance password]
 (let [balance (atom starting-balance)]
  (letfn [(withdraw [amount]
            (if (>= @balance amount)
                (do (swap! balance #(- % amount))
                    @balance)
                "insufficient-funds"))
           (deposit [amount]
             (swap! balance #(+ % amount))
             @balance)
           (dispatch [supplied-pw action]
             (if (= supplied-pw password)
                 (cond (= action 'withdraw) withdraw
                       (= action 'deposit) deposit
                       :else (Error. (str "Unknown request -- MAKE-ACCOUNT " action)))
                 (fn [x] "incorrect-password")))]
    dispatch)))


(deftest test-with-correct-password
 (let [acc (make-account 100 'secret-password)]
  (is (= 60 ((acc 'secret-password 'withdraw) 40)))
  (is (= "insufficient-funds" ((acc 'secret-password 'withdraw) 400)))
  (is (= 100 ((acc 'secret-password 'deposit) 40)))))

(deftest test-with-incorrect-password
 (let [acc (make-account 100 'secret-password)]
  (is (= "incorrect-password" ((acc 'wrong-pw 'deposit) 50)))))

(run-tests)
