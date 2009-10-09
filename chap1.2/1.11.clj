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
(f 5)

; Iterative

(defn f-iter [n t]
 (if (< n 3) (+ n t)
  (f-iter (- n 1)
     (+ (* 2 (- n 2))
        (* 3 (- n 3))))))

(f-iter 1 0)
(f-iter 2 0)
(f-iter 3 0)
(f-iter 4 0)

