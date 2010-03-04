
(ns test-ex3-35
  (:use ex3-35
	clojure.test
	constraint-system))

(deftest squarer-squares
  (let [a (make-connector)
	b (make-connector)]
    (make-squarer a b)
    (set-value! a 4 :user)
    (is (= 16 (get-value b)))))

(deftest squarer-takes-square-root
  (let [a (make-connector)
	b (make-connector)]
    (make-squarer a b)
    (set-value! b 16 :user)
    (is (= 4 (get-value a)))))
