
(ns simple-simulation
  (:use circuit-building-blocks
	agenda))

(defn probe [name wire]
  (add-action! wire
	       (fn []
		 (println "Name=" name
			  "Cur time=" (current-time the-agenda)
			  "New-value=" (get-signal wire)))))

(def input-1 (make-wire))
(def input-2 (make-wire))
(def sum (make-wire))
(def carry (make-wire))
(probe :sum sum)
(probe :carry carry)

(half-adder input-1 input-2 sum carry)
(set-signal! input-1 1)
(propagate)
(set-signal! input-2 1)
(propogate)
