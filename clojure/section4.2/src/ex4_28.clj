; exercise 4.28

(ns ex4-48
  (:use section4))

(interpret '(define (p f x y z)
               (f x y z)))

(interpret (define (sum x y z)
             (+ x y z)))

(interpret (p sum 1 2 3))

; The above works.

; We need to get the actual value because otherwise when
; p is passed f to evaulaute it would be evaluating a thunk
; and not a function. Hence need to use the book version which
; uses actual-value and not eval.