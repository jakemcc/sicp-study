; Exercise 3.63

; Louis Reasoner's sqrt-stream implementation

; (define (sqrt-stream x)
;   (cons-stream 1.0
;                (stream-map (lambda (guess)
;                              (sqrt-improve guess x))
;                            (sqrt-stream x))))

; Original
; (define (sqrt-stream x)
;   (define guesses
;     (cons-stream 1.0
;                  (stream-map (lambda (guess)
;                                (sqrt-improve guess x))
;                              guesses)))
;   guesses)

; Loius Reasoner's implementation ends up calling itself recursively. This call
; is not memoized causing more work to be done.
