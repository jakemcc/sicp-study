; Exercise 2.27
; NOT COMPLETE
;
; Modify reverse procedure from 2.18 to make a deep-reverse
; procedure that takes a list as arguments and returns
; as its value the list with its elements reversed
; and with all sublists deep-reversed as well.
;
; For example:
; (def x (list (list 1 2) (list 3 4)))
; (reverse x)
; -> ((3 4) (1 2))
; (deep-reverse x)
; -> ((4 3) (2 1))


(def x '((1 2) (3 4)))

(reverse x)

(defn deep-reverse [s]
 (cond (empty? s) s
       (not (seq? s)) s
       :else
         ((reverse s)
          (deep-reverse (first s)))

(deep-reverse '(1 2 3 4))
(deep-reverse x)

