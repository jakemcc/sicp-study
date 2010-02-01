(ns test_ex3_17
 (:use ex3_17
       mypair
       clojure.test))

(def simple-three (my-list :a :b :c))

(def three-fakes-four
 (let [a (make-pair :a)
       b (make-pair :b)
       c (make-pair :c)]
  (set-cdr! a b)
  (set-car! b c)
  (set-cdr! b c)
  a))

(def three-fakes-seven
 (let [a (make-pair :a)
       b (make-pair :b)
       c (make-pair :c)]
  (set-car! a b)
  (set-cdr! a b)
  (set-car! b c)
  (set-cdr! b c)
  a))
 
(deftest should-handle-one
 (is (= 1 (count-pairs (make-pair))))
 (is (= 1 (count-pairs (make-pair :a)))))

(deftest should-all-count-as-three
 (is (= 3 (count-pairs simple-three)))
 (is (= 3 (count-pairs three-fakes-four)))
 (is (= 3 (count-pairs three-fakes-seven))))
