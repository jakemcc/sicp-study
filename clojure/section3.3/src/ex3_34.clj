; Exercise 3.34

; Q.
; What is flawed about doing the following to make a squarer?
; (defn squarer [a b]
;   (multiplier a a b))

; A.
; You cannot backout what the value of a is given the value of b
; with the above setup. The multiplier constraint expects to see
; two of its connectors with values to back out the third. It was
; not written to know if two of its connectors are the same.