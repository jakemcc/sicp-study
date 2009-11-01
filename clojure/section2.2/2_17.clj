; Exercise 2.17

; Define a procedure last-pair that returns the list that contains
; only the lasts element of a given (nonempty) list

; Example from book:
; (last-pair (list 23 72 149 34))
; (34)

; Using clojure's last
(defn last-pair [l]
 (list (last l)))

(last-pair (list 23 72 149 34))


; Using equivalent Clojure procedures as Scheme
; mentioned ones in SICP book up to this point
(defn last-pair [l]
 (list 
  (loop [x (first l)
         xs (rest l)]
        (if (empty? xs)
            x
            (recur (first xs) (rest xs))))))

(last-pair (list 23 72 149 34))
