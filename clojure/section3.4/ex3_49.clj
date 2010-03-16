; Exericse 3.49

; Deadlock-avoidance through the numbering of locks and aquiring in
; an agreed upon order will fail if you don't know all the locks
; a process will need to acquire at the beginning.  This could lead to
; a process acquiring lock 2, doing some work, and then realizing it needs
; lock 1 while another process has lock 1 and is waiting on lock 2.