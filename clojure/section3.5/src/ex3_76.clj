; Exercise 3.76

(ns ex3-76
  (:use ex3-74))

(defn average-every-two [s]
  (map #(/ (+ %1 %2) 2) (cons 0 s) s))

(defn smoothed-zero-crossings [s smooth]
  (map sign-change-detector (cons 0 (smooth s)) (smooth s)))

(defn smooth-by-averaging [s]
  (smoothed-zero-crossings s average-every-two))
