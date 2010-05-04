; Exercise 4.19

; I actually like Ben's point of view.  This probably comes from a background
; of doing FPGA programming and a view that this type of assignment can happen all at
; the same time.  I would say that in his defines are truely being done at the
; same time.


; To implement Eva's idea you could scan what is being assigned to the vars
; for other vars that are being assigned at the same time and 
; order the assignment in a way where it works. This is a tricky problem though
; and would be a complex algorithm.  Dealing with mutal recursion would be
; difficult.