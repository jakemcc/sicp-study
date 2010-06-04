; Exercise 4.26

; It is very simple to imagine implementing unless as a
; special form (macro). We would simply need to transform
; unless into an if statement prior to evaluating it. I'm not
; going to bother to write this simple code.


; You may not want unless to be a special form if you could want
; to pass it into another function. This is at least not possible
; to pass a macro into a clojure function.