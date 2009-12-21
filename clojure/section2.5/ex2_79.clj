; Exercise 2.79
; The below code wont run.  Have not acctually defined all of the
; procedures used by the below code.  This is more of a thought
; exercise with some example code.

(defn equ? [x y]
 (apply-generic 'equ? x y))

; Put this with the scheme-number installers
(put 'equ? '(scheme-number scheme-number)
 (fn [x y] (= x y)))

; Put this with the rational package installers
(put 'equ? '(rational rational)
 (fn [x y] (= (* (numer x) (denom y))
              (* (numer y) (numer x)))))

; Put in complex installers
(put 'equ? '(complex complex)
 (fn [x y] (and (= (real-part x) (real-part y))
                (= (imag-part x) (imag-part y)))))
