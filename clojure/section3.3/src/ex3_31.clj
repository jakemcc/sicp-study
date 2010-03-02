; Exercise 3.31

; Q. Why is is necessary that accept-action-procedure! in make-wire
;    specifies that the added procedure is immediately run?

; A. The procedure needs to be immediately run so that the inputs get
;    captured at the time the unit (or, and, or inverter, etc) is added
;    to the circuit. It is also the action-procedures which actually
;    adds the action to the agenda after the correct time.

;    If this didn't happen then the initial values of signals would not
;    go through the logic gates.