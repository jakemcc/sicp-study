(ns sicp.exercise1.11)
; Exercise 1.11

; f(n)  = n if n < 3
;       = f(n - 1) + 2*f(n - 2) + 3*f(n - 3) if n >= 3


; Write recursive process
(defn f [n]
 (if (< n 3) n
  (+ (f (- n 1))
     (* 2 (f (- n 2))) 
     (* 3 (f (- n 3))))))

(f 3)
(f 4)
(f 5)
(f 6)

; Iterative

(defn f2 [n]
 (let [f-iter (fn [a b c count]
               (if (< count 3)
                a
                (recur (+ a (* 2 b) (* 3 c)) a b (dec count))))]
  (if (< n 3) n
  (f-iter 2 1 0 n))))

(f2 3)
(f2 4)
(f2 5)
(f2 6)


