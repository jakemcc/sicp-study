; Building blocks for curcuit simultions

(ns circuit-building-blocks
  (:use agenda))

(defn- call-each
  [procedures]
  (when procedures
    ((first procedures))
    (recur (next procedures))))

(defn get-signal
  [wire]
  (wire :get-signal))

(defn set-signal!
  [wire new-value]
  ((wire :set-signal!) new-value))

(defn add-action!
  [wire action-proc]
  ((wire :add-action!) action-proc))

(defn make-wire []
  (let [signal-value (atom 0)
	action-procedures (atom '())]
    (letfn [(set-my-signal!
	     [new-value]
	     (while (not (= new-value @signal-value))
		    (swap! signal-value (fn [x] new-value))
		    (call-each @action-procedures)))

	    (accept-action-procedure!
	     [proc]
	     (swap! action-procedures #(cons proc %))
	     (proc))

	    (dispatch
	     [m]
	     (cond (= m :get-signal) @signal-value
		   (= m :set-signal!) set-my-signal!
		   (= m :add-action!) accept-action-procedure!
		   :else (Error. "Unkown operation -- WIRE" m)))]
      dispatch)))

(defn make-wires [n]
  (for [x (range n)]
    (make-wire)))

(defn logical-not [s]
  (cond (= s 0)	1
	(= s 1) 0
	:else (Error. "Invalid signal")))

(defn inverter
  [input output]
  (letfn [(invert-input
	   []
	   (let [new-value (logical-not (get-signal input))]
	     (after-delay inverter-delay
			  (fn []
			    (set-signal! output new-value)))))]
    (add-action! input invert-input)))

; For tersness not going to type out the entire truth table.
; Should and could do this using build in and.....
(defn logical-and
  [a b]
  (cond (and (= a 1) (= b 1)) 1
	:else 0))

(defn and-gate
  [a1 a2 output]
  (letfn [(and-action-procedure
	   []
	   (let [new-value
		 (logical-and (get-signal a1)
			      (get-signal a2))]
	     (after-delay and-gate-delay
			  (fn []
			    (set-signal! output new-value)))))]
    (add-action! a1 and-action-procedure)
    (add-action! a2 and-action-procedure)))

(defn logical-or [a b]
  (or a b))

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
    (add-action! a1 or-action-procedure))
  :ok)

(defn half-adder
  [a b s c]
  (let [d (make-wire), e (make-wire)]
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)
    :ok))

(defn full-adder [a b c-in sum c-out]
  (let [s (make-wire)
	c1 (make-wire)
	c2 (make-wire)]
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)
    :ok))
