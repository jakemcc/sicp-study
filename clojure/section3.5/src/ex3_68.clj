; Exercise 3.68

(ns ex3-68)


; Louis Reasoner does this:
(defn pairs [s t]
  (interleave
   (map (fn [x] (list (first s) x))
        t)
   (pairs (next s) (next t))))

; Does this work? Why or why not?

; This doesn't work because it lacks lazy evaluation. Interleave goes to evaluate its second
; paramenter (pairs (next s) (next t)) which then causes it to go into an infinite recursion
; resulting in a stack overflow.

