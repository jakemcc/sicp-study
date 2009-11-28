; Exercise 2.55
;
; Eva Lu Ator types the following
; (car ''abracadarbra)
;
; A quote is printed back.  Why?
;
; (car (quote (quote abracadabra)))
;
; A quote is printed back because abracadabra has two single
; quotes in front of it.  The first quote
; is quoting the second, causing it not to be evaluated.
; This results in the word quote being printed to the screen.

(println (first ''abracadabra))
