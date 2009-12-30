; Exercise 2.83

; write functions to coerce integer -> rational -> real -> complex
; Leaving this as more of a thought exercise, code clearly
; does not work.

; Put in integer package
(defn integer->rational [x]
 (make-rational x 1))

(put 'raise '(integer) #(integer->rational %))

; Put in rational package
(defn rational->real [x] 
 (make-real (double (/ (numer x) (denom x)))))

(put 'raise '(rational) #(rational->real %))

; Put in real package
(defn real->complex [x] 
 (make-from-real-imag x 0))

(put 'raise '(real) #(real->complex %))
