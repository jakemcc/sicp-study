
(ns ex4-21-test
  (:use ex4-21
        clojure.test))

(deftest can-use-lambdas-to-recurse
  (is (= 3628800
         (factorial 10))))

(deftest can-calculate-fibonacci-numbers
  (are [a x] (= a (fibonacci x))
       1 1
       1 2
       2 3
       3 4))

(deftest can-tell-if-even
  (are [a x] (= a (is-even? x))
       true  2
       false 3))

