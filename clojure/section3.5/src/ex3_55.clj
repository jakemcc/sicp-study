; Exercise 3.55

(ns ex3-55)

(comment "this also worked but I thought it was ugly. Attempt 1."
	 (defn partial-sum
	   [s]
	   ((fn ps [a b]
	      (cons a (lazy-seq (ps (+ a (first b))
				    (rest b)))))
	    (first s)
	    (rest s))))

(defn partial-sum [s]
  (lazy-seq
   (cons
    (first s)
    (map +
	 (next s)
	 (partial-sum s)))))
