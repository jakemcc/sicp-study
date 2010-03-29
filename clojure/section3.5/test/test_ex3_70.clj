; Tess for ex3.70

(ns test-ex3-70
  (:use ex3-70
	clojure.test))

(deftest test-merge-weighted
  (is (= '(1 1 2 2) (merge-weighted identity '(1 1) '(2 2))))
  (is (= '(2 2 1 1) (merge-weighted - '(1 1) '(2 2)))))



