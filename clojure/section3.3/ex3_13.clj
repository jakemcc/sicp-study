; Exercise 3.13

; Dummy clojure function
(defn make-cycle [x]
 (set-cdr! (last-pair x) x)
 x)

; Draw a box on pointer diagram for the structure z created by
(def z (make-cycle (list 'a 'b 'c)))


; z --> * * * * *   * * * * *   * * * * *
;       * a *   *-->* b *   *-->* c *   *--|
;       * * * * *   * * * * *   * * * * *  |
;         ^                                |
;         |---------------------------------

; Q) What happens if we try to compute (last-pair z)?
;
; A) Infinate loop
