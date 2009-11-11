; Exercise 2.38

(defn fold-right [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (fold-right op initial (rest sq)))))

(defn fold-left [op initial sq]
 (loop [result initial
        remaining sq]
   (if (empty? remaining)
	   result
	   (recur (op result (first remaining))
		      (rest remaining)))))

(println (fold-right / 1 (list 1 2 3)))
(println (fold-left / 1 (list 1 2 3)))
(println (fold-right list nil (list 1 2 3)))
(println (fold-left list nil (list 1 2 3)))

; If an operation is associative then fold-right
; and fold-left will have the same output
(println (fold-right + 1 (list 1 2 3)))
(println (fold-left + 1 (list 1 2 3)))

