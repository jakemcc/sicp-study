; Exercise 4.29


; A sample program which would run more slowly without memoization
; would be a simple recurisive factorial problem.

(comment
  (define (fact n)
    (if (= 0 n)
      1
      (* n (fact (- n 1))))))

(comment
  (define (square x)
    (* x x))
;;; L-Eval input:
  (square (id 10))
;;; L-Eval value:
  <response>
;;; L-Eval input:
  count
;;; L-Eval value:
  <response>)

; if memoized then the first response is 100 and the second is 1.
; if not memoized then the first is still 100 but the second is 2.