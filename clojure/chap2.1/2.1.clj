; Exercise 2.1

; Define a better version of make-rat which
; handles constructing a rational number
; with both positive and negative numbers

(defn gcd [a b]
 (if (= b 0) a
     (recur b (rem a b))))

(defn make-rat [n d]
 (let [g (gcd n d)]
  (list (/ n g) (/ d g))))

(def numer first)
(def denom last)

(defn print-rat [x]
 (println (str (numer x) "/" (denom x))))

(print-rat (make-rat 4 10))
(print-rat (make-rat -4 10))
(print-rat (make-rat -4 -10))
(print-rat (make-rat 4 -10))

; I didn't have to change anything in the
; make-rat implementation. GCD returns the
; appropiate signed negative number which causes
; the division to cause the correct signs be on
; the numbers
