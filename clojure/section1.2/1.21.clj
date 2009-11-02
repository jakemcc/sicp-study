; Exercise 1.21
(ns exercise1.21)

(defn divides? [a b]
 (= (mod b a) 0))

(defn square [x] (* x x))

(defn find-divisor [n test-divisor]
 (cond (> (square test-divisor) n) n
       (divides? test-divisor n) test-divisor
       :else (recur n (+ test-divisor 1))))

(defn smallest-divisor [n]
 (find-divisor n 2))

(smallest-divisor 199)
; 199
(smallest-divisor 1999)
; 1999
(smallest-divisor 19999)
; 7
