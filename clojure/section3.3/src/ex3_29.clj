; Exercise 3.29

(ns ex3_29
  (:use circuit-building-blocks)

(defn or-gate [a1 a2 output]
  (let [a (make-wire)
	b (make-wire)
	c (make-wire)]
    (inverter a1 a)
    (inverter a2 b)
    (and-gate a b c)
    (inverter c output))
  :ok)

; Delay is 3 interter delays + 1 and-gate delay.
; Compare to exercise 3.28 which only had one or-gate delay
