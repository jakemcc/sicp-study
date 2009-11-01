; Exercise 2.18
;
; Define a procedure 'reverse' that takes a list as argument
; and returns a list of the same element in reverse order

; (reverse (list 1 4 9 16 25))
; (25 16 9 4 1)

; This just works in Clojure since the reverse procedure already
; exists
(reverse (list 1 4 9 16 25))

; Here is my own version

(defn my-reverse [l]
 (loop [nl (list (first l))
        ol (rest l)]
        (if (empty? ol)
            nl
            (recur (cons (first ol) nl)
                   (rest ol)))))


(my-reverse (list 1 4 9 16 25))
