; Exercise 1.41

; Define a procedure called double (my-double) which takes a procedure
; as one argument and retuns a procedure that applies the 
; original twice

(defn my-double [f]
 (fn [x] (f (f x))))


; What value is returned by
; (((my-double (my-double my-double)) inc) 5)
; 21 

(((my-double (my-double my-double)) inc) 5)
; 21

; Using clojures comp
(defn dub [f]
 (comp f f))

(((dub (dub dub)) inc) 5)
