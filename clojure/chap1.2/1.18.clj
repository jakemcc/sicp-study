; Exercise 1.18

; Write an iterative multiplcation function
; with a run time of O(log b)

(defn doub [x] (* x 2))
(defn halve [x] (/ x 2))

(defn my-mult [a b]
 (let [my-mult-iter (fn [a b accum]
                     (cond (= b 1) (+ a accum)
                           (even? b) (recur (doub a) (halve b) accum)
                           :else (recur a (dec b) (+ accum a))))]
  (if (= b 0) 0
      (my-mult-iter a b 0))))

(defn print-mult [a b]
 (do println (str a " * " b " = " (my-mult a b))))

(print-mult 2 0)
(print-mult 0 2)
(print-mult 2 1)
(print-mult 2 5)
(print-mult 1 5)
(print-mult 3 7)
(print-mult 3 8)
