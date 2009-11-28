; Exercise 2.53

; What would the interpreter print in response
; of the following expressions?

;(list 'a 'b 'c)
; (a b c)
(println (list 'a 'b 'c))

;(list (list 'george))
; ((george))
(println (list (list 'george)))

;(cdr '((x1 x2) (y1 y2))) -> (rest '((x1 x2) (y1 y2)))
; ((y1 y2))
(println (rest '((x1 x2) (y1 y2))))

;(cadr '((x1 x2) (y1 y2))) -> (first (rest '((x1 x2) (y1 y2))))
; (y1 y2)
(println (first (rest '((x1 x2) (y1 y2)))))

;(pair? (car '(a short list)))
; false
(println (seq? (first '(a short list))))

(defn memq [item x]
 (cond (empty? x) false
       (= item (first x)) x
       :else (memq item (rest x))))
; (memq 'red '((red shoes) (blue socks))
; false
(println (memq 'red '((red shoes) (blue socks))))

; (memq 'red '(red shoes blue socks))
; true
(println (memq 'red '(red shoes blue socks)))
