; Exercise 4.14

; Eva wrote her version in the interpreted Scheme. Since the Scheme
; we can interpret is fairly complete assuming her implementation
; of it is correct then there is no reason why it should not work.

; Louis mixed the interpreted language with the host language (language
; which is driving the interpreter). This won't work as map takes a function
; as a parameter, which in this case would be written in the interpretted
; Scheme but the implementation of map would be in the host language. The
; host languages map expects the function it is applying to be in the host langauge.
; Applying the function in the host language is done differently then
; applying it in the interpretted language.