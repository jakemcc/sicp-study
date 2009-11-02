; Exercise 2.5
;
; Show that we can represent pairs of nonnegative integers
; using only numbers and arithmetic operations
; if we represent the pair a and b as the interger
; that is produced by 2^a * 3^a.
;
; Define cons, car, and cdr

(defn- pow [x n]
 (Math/pow x n))

(defn- logn [x n]
 (/ (Math/log x)
    (Math/log n)))

(defn my-cons [a b]
 (* (pow 2 a)
    (pow 3 b)))

; P = 2^a*3^b
; 2^a is always even. 
; 3^b is always odd.
; This means the largest even result of 2^a which divides
; evenly into p gives us what 'a equals.
; Similarily for finding b.
(defn- find-highest-even-exponent
 "Returns maximum exponent which allows passed in
 max value to be divided evently by base^exp"
 [base next-exp max-value]
 (if (= 0 (rem max-value (pow base next-exp)))
     (recur base (inc next-exp) max-value)
     (dec next-exp)))

(defn my-car [z]
 (find-highest-even-exponent 2 1 z))

(defn my-cdr [z]
 (find-highest-even-exponent 3 1 z))

(my-car (my-cons 4 5))
(my-cdr (my-cons 4 5))
