; Exercise 1.9

(defn my-plus [a b]
 (if (= a 0)
  b
  (inc (my-plus (dec a) b))))

(my-plus 4 5)

; + is equal to my-plus above, is + in SICP
; (+ 4 5)
; (inc (+ 3 5))
; (inc (inc (+ 2 5)))
; (inc (inc (inc (+ 1 5))))
; (inc (inc (inc (inc (+ 0 5)))))
; (inc (inc (inc (inc 5))))
; (inc (inc (inc 6)))
; (inc (inc 7))
; (inc 8)
; 9

(defn my-plus2 [a b]
 (if (= a 0)
  b
  (my-plus (dec a) (inc b))))

(my-plus2 4 5)

; + is equal to my-plus2 above, is + in SICP
; (+ 4 5)
; (+ 3 6)
; (+ 2 7)
; (+ 1 8)
; (+ 0 9)
; 9
