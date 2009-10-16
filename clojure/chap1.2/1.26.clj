; Exercise 1.26

; With this code (* (expmod base (/ exp 2) m) (expmod base (/ exp 2) m)
; expmod gets called twice.
;
; (square (expmod base (/ exp 2) m)
; With the above code expmod only gets called once.
;
; Doubling the number of times expmod gets called brings the O(log n) proccess
; to O(n)
