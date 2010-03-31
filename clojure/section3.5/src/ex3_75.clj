; Exercise 3.75

(ns ex3-75
  (:use ex3-74))

; Issue is that Reasoner's code is using the last average
; in computing the next average. He should be using the
; previous input signal value.

(defn average [x y]
  (/ (+ x y) 2))

(defn make-zero-crossing [input-stream last-value last-avpt]
  (let [avpt (average (first input-stream) last-value)]
    (lazy-seq
     (cons (sign-change-detector avpt last-avpt)
           (make-zero-crossing (next input-stream)
                               (first input-stream)
                               avpt)))))
