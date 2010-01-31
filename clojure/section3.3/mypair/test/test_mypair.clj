(ns test-mypair
 (:use clojure.test
       mypair))

(deftest can-make-pair
 (let [default (make-pair)
       specified (make-pair :a :b)]
  (is (= nil (car default)))
  (is (= nil (cdr default)))
  (is (= :a (car specified)))
  (is (= :b (cdr specified)))))

(deftest can-change-car
 (let [pair (make-pair)]
  (set-car! 4 pair)
  (is (= 4 (car pair)))))

(deftest can-change-cdr
 (let [pair (make-pair)]
  (set-cdr! 4 pair)
  (is (= 4 (cdr pair)))))

(deftest can-cons-pairs-together
 (let [a (make-pair :a nil)
       b (make-pair :b nil)
       c (my-cons a b)]
  (is (= :a (car c)))
  (is (= b (cdr c)))
  (is (= :b (car (cdr c))))))

(deftest can-print-my-pairs
 (let [a (make-pair :a nil)
       b (make-pair :b nil)
       c (my-cons a b)]
  (is (= "(:a :b )" (create-visual c)))
  (is (= "((:a ) :b )"
         (create-visual
            (my-cons (make-pair a nil) b))))))

(deftest can-make-list-using-pairs
 (is (= nil (car (my-list nil))))
 (is (= :a (car (my-list :a))))
 (is (= :b (car (cdr (my-list :a :b)))))
 (is (= :c (car (cdr (cdr (my-list :a :b :c)))))))
