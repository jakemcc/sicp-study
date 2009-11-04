; Exercise 2.28

; Write a procedure named fringe that takes as an argument
; a tree (represented as a list) and returns a list
; whose elements are all the leaves of the tree arranged in
; left-to-right order.
;
; Example: 
; (def x '((1 2) (3 4)))
; (fringe x)
; (1 2 3 4)

(defn fringe [x]
 (cond (not (seq? x)) (list x)
       (empty? x) nil
       :else 
         (concat (fringe (first x))
                 (fringe (rest x)))))

(fringe '(1 2 3))
(def x '((1 2) (3 4)))
(fringe x)
