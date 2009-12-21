; Exercise 2.77

; This works because magintude looks at its argument
; and figures out based on the type of the argument what
; selector should be called.
;
; First apply-generic is used to get into the 'complex
; package.  Then another apply-generic brings us into
; the rectangular package which is where the correct magnitude
; would be invoked.
;
; apply-generic is called twice.
