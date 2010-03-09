; Exercise 3.40

; Give all possible values of x that can result from:

; (define x 10)
; (parallel-execute (lambda () (set! x (* x x)))
;                   (lambda () (set! x (* x x x))))

; Potentials
; 1000000 -> P1 runs to completion then P2
; 1000000 -> P2 then P1
; 100 -> P2 reads 10 for x but no set, P1 reads 10 for x but no set, P2 sets, P1 sets
; 1000 -> P1 reads 10 for x but no set, P2 reads 10 for x but no set, P1 sets, P2 sets


; If it gets changed to:
; (define x 10)
; (define s (make-serializer))
; (parallel-execute (s (lambda () (set! x (* x x))))
;                   (s (lambda () (set! x (* x x x)))))

; Now P1 and P2 are serialized, once one starts it can't be stopped by the other one.
; Hence only answer is 1000000.
