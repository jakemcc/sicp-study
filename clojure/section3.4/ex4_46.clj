; Exercise 4.46.

; Explain why:
;1 (define (test-and-set! cell)
;2   (if (car cell)
;3       true
;4       (begin (set-car! cell true)
;5              false)))
; has issues if there is no attempt in making test-and-set! atomic.

; Two process could call test-and-set! with the saem cell.
; P1 executes line 2 and sees that the cell hasn't been taken yet but
; before it can set it to true P2 interupts and also executes line 2.
; Now both proess think they can aquire the mutex and they both do
; causing catasrophe.