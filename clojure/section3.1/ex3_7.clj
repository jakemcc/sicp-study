; Exercise 3.7
; Add the ability to have joint bank accounts to exercise 3.3.

(use 'clojure.test)

;{{{ make-account from exercise 3.3
(defn make-account [starting-balance password]
 (let [balance (atom starting-balance)]
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
                 (cond (= action 'withdraw) withdraw
                       (= action 'deposit) deposit
                       :else (Error. (str "Unknown request -- MAKE-ACCOUNT " action)))
                 (fn [x] "incorrect password")))]
    dispatch)))
;}}}


(defn make-joint
 "Makes a bank account jointly owned"
 [account owners-pw joint-pw]
 (fn [password action]
  (if (= joint-pw password) (account owners-pw action)
      (Error. (str "BAD JOINT PASSWORD -- " password)))))

(deftest can-make-joint-account
 (let [orig-acc (make-account 100 :password)
       joint-acc (make-joint orig-acc :password :joint)]
  (is (= 91 ((orig-acc :password 'withdraw) 9)))
  (is (= 80 ((joint-acc :joint 'withdraw) 11)))))

(run-tests)

; vim:set foldmethod=marker
