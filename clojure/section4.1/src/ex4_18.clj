; Exercise 4.18

; No this will not work. When <e2> is assigned to b
; that will evaluate the y in the definition is expected
; to be there. It will not be there as it hasn't (set! u a)
; hasn't been evaluated yet.


; This will work with the original. By the time <e2> would be evaled
; <e1> has already been setup so it will work