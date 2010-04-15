
(ns section4-test
  (:use clojure.test
        section4))

(deftest test-self-eval
  (is (= 5 (interpret 5)))
  (is (= "hey" (interpret "hey"))))
