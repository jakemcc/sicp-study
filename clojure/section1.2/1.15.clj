; Exercise 1.15

;(ns exercise1.15)

(defn cube [x] (* x x x))

(defn p [x]
 (- (* 3 x)
    (* 4 (cube x))))

(defn abs [x] (if (< x 0) (- x) x))

(defn sine [angle]
 (if (not (> (abs angle) 0.1))
     angle
     (p (sine (/ angle 3.0)))))


; a) How many times is p called when (sine 12.15) is evaluated?
; (sine 12.15)
; 5 times

; b) What is the order of growth in space and number of steps when (sine a) is evaluated
; number of steps: O(log a), as input a doubles, p increases linearly
; space: O(log a), same thing, since calls to p  increase linearly and that is what is left to calculate with each step the growth in space grows linearly
(sine 2.0)
(sine 4.0)
(sine 8.0)
(sine 16.0)
(sine 32.0)

