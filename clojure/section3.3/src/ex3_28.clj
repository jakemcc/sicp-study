; Exercise 3.28

(ns circuit)

(defn or-gate [a1 a2 output]
  (letfn [(or-action-procedure
	   []
	   (let [new-value (logical-or
			    (get-signal a1)
			    (get-signal a2))]
	     (after-delay or-gate-delay
			  (fn []
			    (set-signal! output new-value)))))]
    (add-action! a2 or-action-procedure)
    (add-action! a1 or-action-procecure))
  :ok)
