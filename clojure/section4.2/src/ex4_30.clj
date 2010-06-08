; Exercise 4.30

; a) Each element gets evaluated as the first argument in the begin s-exp.
;    The display s-exp causes the value to be realized since display is
;    primitive.

; b) original eval-sequence:
;       (p1 1) => '(1 2)
;       (p2 1) => 1
;    new eval-sequence:
;       (p1 1) => '(1 2)
;       (p2 1) => '(1 2)

; c) The example already calls a primitive on each element
;    in the list. Calling actual-value on it as well doesn't do anything
;    bad.

; d) I actually like Cy's approach better.  It seems to be like it handles
;    side effects in a better way. As someone writing code I don't want to
;    have the burden of thinking about if all of my expressions are going
;    to be executed or not.