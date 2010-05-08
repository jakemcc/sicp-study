; Exercise 4.24

(ns ex4-24
  (:require [section4 :as s4]
            [section4-optimized :as s4o]))

(def func
     '(define (add-positive x y)
        (if (or (< x 0) (< y 0)) (Error. "Must add positive numbers"))
        (if (= x 0)
          y
          (add-positive (- x 1) (+ y 1)))))

(s4/interpret func)
(s4o/interpret func)

(println "Time for unoptimized: ")
(time (dotimes [x 30] (s4/interpret '(add-positive 20 35))))

(println "Time for optimized: ")
(time (dotimes [x 30] (s4o/interpret '(add-positive 20 35))))

;Time for unoptimized: 
;"Elapsed time: 231.454 msecs"
;Time for optimized: 
;"Elapsed time: 98.1 msecs"

; Looks like the original evaluated wastes a lot of time
; analyzing the same expressions multiple times

