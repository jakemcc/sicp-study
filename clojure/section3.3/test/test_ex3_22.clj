(ns test_ex3_22
 (:use ex3_22
       clojure.test))

(deftest should-detect-empty
 (= true ((make-queue) :empty?)))

(deftest can-insert-and-remove
 (let [q (make-queue)]
  (q :insert! :a)
  (is (= :a (q :front)))
  (q :delete!)
  (is (= true (q :empty?)))
  ((q :insert! :b) :insert! :c)
  (is (= :b (q :front)))
  (q :delete!)
  (is (= :c (q :front)))))
