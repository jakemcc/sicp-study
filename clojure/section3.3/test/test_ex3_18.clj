(ns test_ex3_18
 (:use clojure.test
       mypair
       ex3_18))

(def has-cycle
 (let [a (make-pair :a)
       b (make-pair :b)
       c (make-pair :c)]
   (set-cdr! a b)
   (set-cdr! b c)
   (set-cdr! c a)
   a))

(def no-cycle
 (let [a (make-pair :a)
       b (make-pair :b)
       c (make-pair :c)]
   (set-cdr! a b)
   (set-cdr! b c)
   a))


(deftest should-detect-cycle
 (is (= true (cycles? has-cycle))))

(deftest should-not-detect-cycle
 (is (= false (cycles? no-cycle))))
