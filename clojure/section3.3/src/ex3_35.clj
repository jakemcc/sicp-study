
(ns ex3-35
  (:use constraint-system
	clojure.contrib.math))

; Going to implement the squarer using similar ways I implemented the
; other constraints

(defn square [x]
  (* x x))

(deftype Squarer [a b]
  Constraint
  (process-new-value
   [this]
   (if (has-value? b)
       (if (< (get-value b) 0)
	   (Error. (str "square less than 0 -- SQUARER " (get-value b)))
	   (set-value! a
		       (sqrt (get-value b))
		       this))
       (when (has-value? a)
	     (set-value! b
			 (square (get-value a))
			 this))))
  (process-forget-value
   [this]
   (forget-value! a this)
   (forget-value! b this)
   (process-new-value this)))

(defn make-squarer [a b]
  (let [squarer (Squarer. a b)]
    (connect a squarer)
    (connect b squarer)
    squarer))
