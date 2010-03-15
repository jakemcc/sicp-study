; Exercise 3.55

(ns ex3-55)

(defn partial-sum
  [s]
  ((fn ps [a b]
     (cons a (lazy-seq (ps (+ a (first b))
			   (rest b)))))
   (first s)
   (rest s)))

; If you want to just do it for the example in book this works.
; Couldn't think of a cool way to do it in a similar fashion					;
; (def ps (lazy-seq (cons (first integers) (map + ps (rest integers)))))


  