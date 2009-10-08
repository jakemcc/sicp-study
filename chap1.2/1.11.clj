; Exercise 1.11

; f(n)  = n if n < 3
;       = f(n - 1) + 2*f(n - 2) + 3*f(n - 3) if n >= 3


; Write recursive process
(defn f [n]
 (if (< n 3) n
  (+ (f (- n 1))
     (* 2 (f (- n 2))) 
     (* 3 (f (- n 3))))))

(f 1)
(f 2)
(f 3)
(f 4)

; Iterative

