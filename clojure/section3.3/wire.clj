; Wire for circuit simulation

(ns wire)

; This doens't actually print done like in the example.
; Change to not use nil punning when and use if to do that if required
(defn- call-each
  [procedures]
  (when procedures
    (car procedures)
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
	     (swap! signal-value (fn [x] new-value))
	     (call-each @action-procedures))

	    (acept-action-procedure!
	     [proc]
	     (swap! action-procedures cons proc)
	     (proc))

	    (dispatch
	     [m]
	     (cond (= m :get-signal) @signal-value
		   (= m :set-signal!) set-my-signal!
		   (= m :add-action!) accept-action-procedure!
		   :else (Error. "Unkown operation -- WIRE" m)))]
      dispatch)))
