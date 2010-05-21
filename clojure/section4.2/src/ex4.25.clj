; Exercise 4.25

; The definition will not work in the applicative order langauge.
; This is because all of the arguments to unless will be evaluated
; when they are passed into unless. This would cause an infinite loop
; as factorial will keep getting called with smaller and smaller values.

; In a normal order langauge this would work. Then arguments would
; only be evaluated when needed. Thi would cause the conditional
; to be evaluated and eventually it would be true, causing
; the factorial call to not be called.