; Exercise 2.26

; Given x and y
(def x (list 1 2 3))
(def y (list 4 5 6))

; What is the result of the following

; Given in section 2.2.1
(defn append [list1 list2]
 (if (empty? list1)
     list2
     (cons (first list1) (append (rest list1) list2))))

; (append x y) -> '(1 2 3 4 5 6)
(append x y)

; (cons x y)  -> '((1 2 3) 4 5 6)
(cons x y)

; (list x y) -> '((1 2 3) (4 5 6))
(list x y)
