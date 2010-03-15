; Exercise 3.53

; Q.
;    Without running it, describe the elements of
;    (define s (cons-stream 1 (add-streams s s)))

; A.
;   s looks like the following
;   '(1 2 4 8 16 ....)
;   The equation f(n) = f(n-1) + f(n-1) describes it.