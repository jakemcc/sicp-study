; Exercise 3.16
;
;
; Show that Ben Bitdiddle's procedure to count pairs is flawed.
;
;(define (count-pairs x)
;  (if (not (pair? x))
;      0
;      (+ (count-pairs (car x))
;         (count-pairs (cdr x))
;         1)))
;
; Give example of 3 pair box-and-pointers that return 3, 4, 7, and
; never return.
;
;
; Returns 3
;
; z-->* * * * *  * * * * *  * * * * *
;     * a *   *->* b *   *->* c * / *
;     * * * * *  * * * * *  * * * * *
;
; Returns 4
;
; z-->* * * * *  * * * * *  * * * * *
;     * a *   *->*   *   *->* c * / *
;     * * * * *  * | * * *  * * * * *
;                  |          ^
;                  -----------|
; Returns 7
;
; z-->* * * * *  * * * * *  * * * * *
;     *   *   *->*   *   *->* c * / *
;     * | * * *  * * * * *  * * * * *
;       |         ^ \         ^
;       ---------/   --------/
;
; Never return
;
; z-->* * * * *  * * * * *  * * * * *
;     * a *   *->* b *   *->* c *   *
;     * * * * *  * * * * *  * * * * *
;       ^                         |
;       |--------------------------
