
(ns test-ex3-55
  (:use clojure.test
	ex3-55))

(def integers (iterate inc 1))

(deftest partial-sum-works
  (let [sums (partial-sum integers)]
    (is (= '(1 3 6 10 15)
	   (take 5 sums)))))


