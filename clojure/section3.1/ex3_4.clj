; Exercise 3.4

(use 'clojure.test)

(defn make-account [starting-balance password]
 (let [balance (atom starting-balance)
       attempts (atom 0)]
  (letfn [(withdraw [amount]
            (if (>= @balance amount)
                (do (swap! balance #(- % amount))
                    @balance)
                "insufficient funds"))
           (deposit [amount]
             (swap! balance #(+ % amount))
             @balance)
           (dispatch [supplied-pw action]
             (if (= supplied-pw password)
                 (do 
                  (swap! attempts (fn [x] 0))
                  (cond (= action 'withdraw) withdraw
                        (= action 'deposit) deposit
                        :else (Error. (str "Unknown request -- MAKE-ACCOUNT " action))))
                 (do
                  (swap! attempts inc)
                  (if (> @attempts 7) 
                      (fn [x] "SIREN SIREN - cops called sucka!")
                      (fn [x] "incorrect password")))))]
    dispatch)))

(deftest test-with-correct-password
 (let [acc (make-account 100 'secret-password)]
  (is (= 60 ((acc 'secret-password 'withdraw) 40)))
  (is (= "insufficient funds" ((acc 'secret-password 'withdraw) 400)))
  (is (= 100 ((acc 'secret-password 'deposit) 40)))))

(deftest should-call-cops-after-seven-bad-passwords
 (let [acc (make-account 100 'secret-password)]
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "SIREN SIREN - cops called sucka!" ((acc 'wrong-pw 'withdraw) 10)))))

(deftest should-reset-cop-call-after-correct-password
 (let [acc (make-account 100 'secret-password)]
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))
  (is (= 80 ((acc 'secret-password 'withdraw) 20)))
  (is (= "incorrect password" ((acc 'wrong-pw 'withdraw) 10)))))

(run-tests)

