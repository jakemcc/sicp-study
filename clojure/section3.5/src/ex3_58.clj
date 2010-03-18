; Exercise 3.58

; Give an interpretation of stream computed by:
; A: Stream is doing division of num by den in the base of radix.
;    Returns points after decimal with each call.

; (define (expand num den radix)
;   (cons-stream
;     (quotient (* num radix) den)
;     (expand (remainder (* num radix) den) den radix)))

(declare quotient)

(defn expand [num den radix]
  (lazy-seq
   (cons
    (quotient (* num radix) den)
    (expand (rem (* num radix) den) den radix))))

(defn quotient [x y] (int (/ x y)))

; What is produced by (expand 1 7 10)?
;user> (take 30 (expand 1 7 10))
;(1 4 2 8 5 7 1 4 2 8 5 7 1 4 2 8 5 7 1 4 2 8 5 7 1 4 2 8 5 7)

; by (expand 3 8 10)?
;user> (take 30 (expand 3 8 10))
;(3 7 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0)
