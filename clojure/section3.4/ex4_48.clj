; Exercise 3.48


; Explain why numbering locks and then trying to aquire the smaller numbered locks
; prior to the higher number locks avoids deadlock.


; Lets say there are two locks: 1 and 2
; Lets say there are two processes: A and B

; Process:      A   B
; First lock:   1   2
; Second lock:  2   1

; If the above happens, (A grabs lock 1, B grabs 2, A trys to get 2 and fails because
; B has it, B fails to get 1 because A has it) then deadlock happens.

; Process:      A   B
; First lock:   1   1
; Second lock:  2   2

; The above shows the processes trying to grab the locks in the same order. This means
; that either A or B will get lock 1 and the other process will be blocked waiting for
; lock 1. Whatever process gets lock 1 will be able to get lock 2 and finish processing
; and will eventually release lock 2 and 1, causing the other process to be able to continue.
