; Exercise 3.43

; Exchanging money between accounts has been serialized in a way
; where the entire transaction has to happen before anything else
; can be done to either account. Because of this the final results
; will be 10, 20, and 30 in order since anytime an exchange happens between
; any of those accounts the same balances (10, 20, 30) are still found.

; In the first version the sum of the balances of the two accounts
; doing the exchange is preserved since the whatever sum is taken from
; one is put into the other.  Because of this even if exchanges are allowed
; to be interleaved as the first example would allow the total of all accounts
; will remain the same though the balances could be something like 10, 10, 40.
