; Exercise 3.19

; Keep traversing list, keep two pointers.
; advance once two spots, advance other one.
; If they ever match up you have a cycle.

(ns ex3_19
 (:use mypair))

(defn cycles? [s]
 (loop [one s, two (cdr s)]
  (cond (= one two) true
        (or (= nil one) (= nil two)) false
        (= nil (cdr two)) false
        :else (recur (cdr one) (cdr (cdr two))))))
