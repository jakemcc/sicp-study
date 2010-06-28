(ns ex4-37)

; My initial reaction is that Ben Bitdiddle's change
; does improve the efficiency.

; It seems to me that because the first 'require check is
; done earlier (only after i and j have been picked, k is still
; unpicked) that the work of chosing a k will not always be done.
; Not doing the work to pick k would cut down on the number of
; possibilities that need to be checked and hence cause an efficiency
; gain