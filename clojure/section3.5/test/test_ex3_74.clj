; Test for ex3.74

(ns test-ex3-74
  (:use ex3-74
        clojure.test))


(deftest detects-sign-changes
  (is (= '(0 0 -1 0 1 0)
         (zero-crossings '(0.01 0.02 -0.01 -0.05 1 1)))))
