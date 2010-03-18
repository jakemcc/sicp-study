; Tests for ex3_59

(ns test-ex3-59
  (:use clojure.test
        ex3-59))

(deftest part-a-works
  (is (= '(1 1/2 1/3 1/4)
         (take 4 (integrate-series (repeat 1))))))




