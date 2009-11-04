; Exercise 2.27

(defn deep-reverse [s]
 (cond (empty? s) nil 
       (seq? (first s))
         (concat (deep-reverse (rest s))
                 (list (deep-reverse (first s))))
       :else
         (concat (deep-reverse (rest s))
                 (list (first s)))))


(defn print-dr [s]
 (println "Before: " s)
 (println "After : " (deep-reverse s)))
 
(print-dr '(1 2 3))
(print-dr '((1 2) (3 4)))
(print-dr '((1 2 (3 4)) (5 6 7)))
