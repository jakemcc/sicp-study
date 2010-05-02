; Exercise 4.13

; Functionality has been added to environment.clj, environment_test.clj,
; section4.clj and section4_test.clj

; I decided to only have the variable become unbound in the current
; scope (whatever the first frame of the environment is). This seems to make
; the most sense to me. Being able to traverse all the frames to remove
; a binding seems like the wrong thing. Especially when you consider that
; a variable could be bound at different frames.