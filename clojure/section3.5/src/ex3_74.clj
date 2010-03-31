; Exercise 3.74

(ns ex3-74)

; Making zero-crossings a function so you can pass
; any stream into it instead of global sense-data

(defn  sign-change-detector [x y]
  (cond (and (neg? x) (pos? y)) 1
        (and (pos? x) (neg? y)) -1
        :else 0))

; So this divirges from question the book is asking.
; This change to zero-crossings makes it behave like
; the original version given before using stream-map.
; The version before using stream-map had 0 be the initial value
; and the below accomplishes the same thing
(defn zero-crossings [s]
  (map sign-change-detector (cons 0 s) s))
