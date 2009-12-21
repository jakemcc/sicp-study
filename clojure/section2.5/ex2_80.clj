; Exercise 2.80

(defn my-zero? [x]
 (apply-generic 'my-zero? x))

; Install for orginary numbers
(put 'my-zero? '(scheme-number) 
 (zero?))

; install for rational numbers
(put 'my-zero? '(rational)
 (fn [x] (zero? (numer x))))

; install for complex numbers
(put 'my-zero? '(complex)
 (fn [x] (and (zero? (real-part complex))
              (zero? (imag-part complex)))))
