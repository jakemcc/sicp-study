; Agenda for circuit simulator

; I didn't actually do this in a similar fashion as in the book.
; The order actions are added to the agenda is not the order things
; at the same time get executed.
(ns agenda)
(def inverter-delay 2)
(def and-gate-delay 3)
(def or-gate-delay 5)

(defn make-agenda []
  (atom {:cur-time 0 :items '()}))

(def the-agenda (make-agenda))

(defn current-time [agenda]
  (:cur-time @agenda))

(defn set-current-time! [agenda time]
  (swap! agenda #(assoc % :cur-time time)))

(defn segments [agenda]
  (:items @agenda))

(defn set-segments! [agenda segments]
  (swap! agenda #(assoc % :items segments)))

(defn first-segment [agenda]
  (first (segments agenda)))

(defn rest-segments [agenda]
  (rest (segments agenda)))

(defn empty-agenda? [agenda]
  (empty? (segments agenda)))

(defn make-time-segment [time action]
  {:time time :action action})
(defn segment-time [s] (:time s))
(defn segment-action [s] (:action s))

(defn add-to-agenda! [time action agenda]
  (letfn [(add-to-segments
	   ([segments]
	      (let [item (make-time-segment time action)]
		(sort-by :time (cons item segments)))))]
    (set-segments! agenda
		   (add-to-segments (segments agenda)))))

(defn after-delay
  [delay action]
  (add-to-agenda! (+ delay (current-time the-agenda))
		     action
		     the-agenda))

(defn remove-first-agenda-item! [agenda]
  (set-segments! agenda (rest-segments agenda)))

(defn first-agenda-item [agenda]
  (if (empty-agenda? agenda)
      (Error. "Agenda is empty -- FIRST-AGENDA-ITEM")
      (let [first-seg (first-segment agenda)]
	(set-current-time! agenda (segment-time first-seg))
	(segment-action first-seg))))

(defn propagate
  []
  (if (empty-agenda? the-agenda)
      :done
      (let [first-item (first-agenda-item the-agenda)]
	(first-item)
	(remove-first-agenda-item! the-agenda)
	(recur))))
