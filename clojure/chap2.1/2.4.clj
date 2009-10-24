; Exercise 2.4

; Show that cons and car implemented as below works as expected
(defn my-cons [x y]
 (fn [m] (m x y)))

(defn my-car [z]
 (z (fn [p q] p)))

; Expect a 1 to be printed
(println (my-car (my-cons 1 2)))

; Implement cdr following these definitions
(defn my-cdr [z]
 (z (fn [p q] q)))

; Expect a 2 to be printed
(println (my-cdr (my-cons 1 2)))

; Show using substituion how my-cdr works
; 
; (my-cdr (my-cons 1 2))
; (my-cdr (fn [m] (m 1 2)))
; ((fn [m] (m 1 2)) (fn [p q] q))
; ((fn [p q] q) 1 2)
; 2
