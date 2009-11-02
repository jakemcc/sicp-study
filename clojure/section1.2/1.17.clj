; Exercise 1.17

; Original O(b)
; (defn my-mult [a b]
;  (if (= b 0)
;      0
;      (+ a (my-mult a (- b 1)))))

(defn doub [x] (* x 2))
(defn halve [x] (/ x 2))

(defn my-mult [a b]
 (cond (= b 0) 0
       (even? b) (my-mult (doub a) (halve b))
       :else (+ a (my-mult a (dec b)))))

(defn print-mult [a b]
 (do println (str a " * " b " = " (my-mult a b))))
(print-mult 2 4)
(print-mult 2 5)
(print-mult 1 5)
(print-mult 3 7)
(print-mult 3 10)

