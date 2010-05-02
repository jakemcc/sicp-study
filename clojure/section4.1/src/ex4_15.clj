; Exercise 4.15

;http://en.wikipedia.org/wiki/Halting_problem

; (define (run-forever) (run-forever))
; (define (try p)
;   (if (halts? p p)
;       (run-forever)
;       'halted))

; If we were to evaluate (try try) then we end up doing
; (halts? try try).

; a) If (halts? try try) returns true -> (run-forever) is called and app runs forever
; b) If (halts? try try) returns false -> 'halted is returned

; in case 'a' halts? claims that (try try) will halt but then it runs forever
; In case 'b' halts? claims it doesn't halt, but then it does.

; Because of these contradictions we know there cannot be a halts? which
; does what the specification asks.