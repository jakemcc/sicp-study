; Exercise 2.20

; Write a procedure same-parity that takes
; one or more integers and returns a list of all the arguments
; that have the saame even-odd parity as the first

(defn same-parity [& l]
 (let [keep? (if (even? (first l))
                 even?
                 odd?)]
  (filter keep? l)))


(same-parity 1 2 3 4 5 6 7)
(same-parity 2 3 4 5 6 7)
