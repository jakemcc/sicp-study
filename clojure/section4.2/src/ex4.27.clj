; Exercise 4.27

;(define w (id (id 10)))
;;; L-Eval input:
;count
;;; L-Eval value:
;<response> = 0
;;; L-Eval input:
;w
;;; L-Eval value:
; <response> = 10
;;; L-Eval input:
;count
;;; L-Eval value:
; <response> = 2

; After the definition of w nothing has happened yet so
; count still equals zero.

; After w is used then (id) gets called twice, causing
; count to be incremented twice.