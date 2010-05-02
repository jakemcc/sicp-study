; Exercise 4.16

; Solved in section4.clj and section4_test.clj

; I ended up doing part c (installing scan-out-defines) in interpreter by adding
; it to make-procedure instead of procedure-body.  I did this so that
; the transformation would only have to happen once instead of everytime
; a procedure body is invoked.