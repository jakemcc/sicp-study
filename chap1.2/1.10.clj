; Exercise 1.10 from SICP

; Ackermann's function
(defn A [x y]
 (cond (= y 0) 0
       (= x 0) (* 2 y)
       (= y 1) 2
       :else (A (- x 1)
                (A x (- y 1)))))

; What are values of the following?
(A 1 10)
; => 1024
(A 2 4)
; => 65536
(A 3 3)
; => 65536 

; What are the following functions in mathematical terms?
(defn f [n] (A 0 n))
; f[n] = 2*n

(defn g [n] (A 1 n))
; g[n] = 2^n

(defn h [n] (A 2 n))


(defn k [n] (* 5 n n))
; k[n] = 5n^2


