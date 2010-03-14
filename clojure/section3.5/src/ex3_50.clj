; Exercise 3.50


; Just going to keep this one in scheme.

; (define (stream-map proc . argstreams)
;   (if (stream-null? (car argstreams))
;       the-empty-stream
;       (stream-cons
;         (apply proc (map stream-car argstreams))
;         (apply stream-map
;                (cons proc (map stream-cdr argstreams))))))