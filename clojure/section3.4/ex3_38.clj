; Exercise 3.38

; Bank account starts with $100.
; The following three transactions have to run,
; Peter: (set! balance (+ balance 10))
; Paul:  (set! balance (- balance 20))
; Mary:  (set! balance (- balance (/ balance 2)))

; a. List the different possible values for balance assuming system forces
;    the three to run sequentially in some order.
;
;    [peter, paul, mary] = 45
;    [peter, mary, paul] = 35
;    [paul, peter, mary] = 45
;    [paul, mary, peter] = 50
;    [mary, peter, paul] = 40
;    [mary, paul, peter] = 40

; b. What other values could be produced if system allows process to be interleaved
;
;    One example: Peter reads in balance as 100 and adds 10, before he can set
;                 balance Peter reads in balance as 100. Paul then sets balance to
;                 110. Mary reads in 110 and figures out she should withdraw 55.
;                 Peter finishes his transaction and subracts 20 from 100 setting balance
;                 to 80. Mary then reads in 80 for balance and subtracts 55 and sets
;                 balance to 25.
;
