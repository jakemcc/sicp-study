(ns test_ex3_19
 (:use clojure.test
       mypair
       ex3_19))

(def has-cycle-odd
 (let [a (make-pair :a)
       b (make-pair :b)
       c (make-pair :c)]
   (set-cdr! a b)
   (set-cdr! b c)
   (set-cdr! c a)
   a))

(def has-cycle-even
 (let [a (make-pair :a)
       b (make-pair :b)
       d (make-pair :d)
       c (make-pair :c)]
   (set-cdr! a b)
   (set-cdr! b c)
   (set-cdr! c d)
   (set-cdr! d a)
   a))

(deftest should-detect-cycle
 (is (= true (cycles? has-cycle-odd)))
 (is (= true (cycles? has-cycle-even))))

(deftest should-not-detect-cycle
 (is (= false (cycles? (my-list :a :b :c))))
 (is (= false (cycles? (my-list :a :b :c :d)))))
