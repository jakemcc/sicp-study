; Exercise 3.33

(ns test-ex3-33
  (:use constraint-system
	clojure.test))

(defn averager [a b c]
  (let [sum (make-connector)
	divisor (make-connector)]
    (make-adder a b sum)
    (make-constant 2 divisor)
    (make-multiplier c divisor sum)))

(deftest averager-outputs-average
  (let [a (make-connector)
	b (make-connector)
	c (make-connector)]
    (averager a b c)
    (set-value! a 12 :user)
    (set-value! b 18 :user)
    (is (= 15 (get-value c)))))

