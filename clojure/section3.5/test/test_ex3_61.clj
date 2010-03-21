
(ns test-ex3-61
  (:use ex3-61
        ex3-60
        clojure.test))


(deftest returns-inverted-series
  (let [s (iterate inc 1)
        res (take 5 (mul-series (invert-unit-series s)
                              s))]
    (is (= '(1 0 0 0 0) res))))



