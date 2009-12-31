; Exercise 2.86

; Complex numbers have two representations, rectangular and polar.
; If we do work at that level of abstraction then code which uses
; complex numbers will simply work.
;
; Methods called at the rectangular/polar accessor level are
; cos, sin, square, atan.  This means we would need to implement
; a generic version of each of those four functions.
;
; Then whatever number types we wanted to allow to be part of
; a complex number would need to implement a specific version
; of those four functions in order to work properly in our
; system.
