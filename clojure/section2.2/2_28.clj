; Exercise 2.28
; NOT COMPLETE

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
 (cond (empty? x) '()
       (not (seq? x)) x
       :else
         (cons (fringe (first x))
               (fringe (rest x)))))

;(def x '((1 2) (3 4)))
; (fringe x)
(fringe '(1 2))
