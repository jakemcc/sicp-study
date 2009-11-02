; Exercise 1.19
(ns exercise1.19 (:use clojure.test))
; Was attempting to use clojure.test, was having issues though, leaving in to hopefully
; have discussion on it

; Compute p' and q'
; Tpq = { a <- bq + aq + ap
;       { b <- bp + aq
; If Tpq is applied twice that is the same as Tp'q'.
; Therefor to figure out p' and q' apply Tpq twice
; Tpq^2 = { a <- bpq + aqq + bqq + aqq + apq + bqp + aqp + app =
            ;               b(pq + qq + qp) + a(pq + qq + qp) + a(pp + qq)
;         { b <- bpp + aqp + bqq + aqq + apq = b(pp + qq) + a(qp + qq + pq)
; Looking at the equations you can see that p' = pp + qq, q' = 2*pq + qq
; Put those into fib-iter and it works


(defn fib-iter [a b p q count]
 (cond (= count 0) b
       (even? count)
        (fib-iter a
                  b
                  (+ (* p p) (* q q)) ; compute p'
                  (+ (* 2 p q) (* q q)) ; compute q'
                  (/ count 2))
        :else
         (fib-iter (+ (* b q) (* a q) (* a p))
                   (+ (* b p) (* a q))
                   p
                   q
                   (- count 1))))
    
(defn fib [n]
 (fib-iter 1 0 0 1 n))

(fib 0) ;0
(fib 1) ;1
(fib 2) ;1
(fib 3) ;2
(fib 4) ;3
(fib 5) ;5
(fib 6) ;8
(fib 7) ;13

