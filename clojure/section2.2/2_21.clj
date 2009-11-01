; Exercise 2.21
;
; The procedure square-list takes a list of numbers as an argument
; and returns the square of each numbers.
;
; Define square-list using map and without using map

(defn square [x] (* x x))

(defn square-list [items]
 (if (empty? items)
     nil
     (cons (square (first items)) 
           (square-list (rest items)))))

(square-list (list 1 2 3 4))

(defn square-list [items]
 (map square items))

(square-list (list 1 2 3 4))
