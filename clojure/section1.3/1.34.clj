; Exercise 1.34
;
; What happens if we have the following
; definition of the procedure f and then
; do (f f).
;
(defn f [g]
 (g 2))

(f f)

; (f f)
; (f 2)
; (2 2)   <- results in an error since 2 is not a function
