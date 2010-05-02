; Exercise 4.17

; When it is scanned out there is an extra environment frame.
; This is because scan-out-defines wraps the body of the method
; in a let to accomplish its goal. A let gets turned into a lambda
; which adds a frame.

; This won't make a difference. Anything that would have previously been
; available to the method body would still be available since its frame
; inherits any vars from the frame that contains it.

; We would need to move the internal definitions to be in the higher scope
; and resist making an extra frame by using let to do so. This would work
; as when the internal definitions get executed they would resolve
; their references to valid values