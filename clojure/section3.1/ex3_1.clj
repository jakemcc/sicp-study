; Exercise 3.1

(use 'clojure.test)

; Should this be doing using refs instead?
(defn make-accumulator [starting-value]
 (let [initial (atom starting-value)]
  (fn [additional] (swap! initial (partial +  additional)))))


(deftest accumulator-accumulates
 (let [accum (make-accumulator 10)
       other (make-accumulator 5)]
  (is (= 15 (accum 5)))
  (is (= 25 (accum 10)))
  (is (= 6  (other 1)))))

(run-tests)
