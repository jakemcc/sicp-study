; Exercise 4.2

; a)
; The eval function originally treats any list that isn't
; caught by one of the special cases before the application?
; check as a procedure to be evaluated.  After Loui's moves
; the application? check before the assignment? check
; then (define x 3) will be evaluated and treated as thought
; define is a procedure to be called with x and 3 as arguments.

; b) If Louis changes it so that application? checks for a tagged list
;    then he could make this work. For example, doing
; (defn application? [exp] (tagged-list? exp 'application)
;    and then changing (operator) and (operands) to handle
;    the addition of a tag could make this work.