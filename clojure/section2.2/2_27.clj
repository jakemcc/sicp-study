; Exercise 2.27

(defn append [list1 list2]
 (if (empty? list1)
     list2
     (cons (first list1) (append (rest list1) list2))))


(defn deep-reverse [s]
 (cond (empty? s) nil 
       (seq? (first s))
         (append (deep-reverse (rest s))
                 (list (deep-reverse (first s))))
       :else
         (append (deep-reverse (rest s))
                 (list (first s)))))


(defn print-dr [s]
 (println "Before: " s)
 (println "After : " (deep-reverse s)))
 
(print-dr '(1 2 3))
(print-dr '((1 2) (3 4)))
(print-dr '((1 2 (3 4)) (5 6 7)))
