; Exercise 3.15
;
; Draw box-point-diagrams to explain set-to-wow! behavior

; Pretend functions exist
;(defn set-to-wow! [s]
;  (set-car! (car s) 'wow))

; Prior to set-to-wow! call
;
; z1-->* * * * *
;      *   *   *
;      * | * | *       
;        v   v
;      * * * * *   * * * * *
; x--->*   *   --->*   * / *
;      * | * * *   * | * * *
;        a           b
;
;
; z2-->* * * * *   * * * * *   * * * * *
;      *   *   --->*   *   --->*   * / *
;      * | * * *   * | * * *   * | * * *
;        |           a           b
;        |         * | * * *   * | * * *
;        --------->*   *   --->*   * / *
;                  * * * * *   * * * * *
;
; After set-to-wow! call
;
; z1-->* * * * *
;      *   *   *
;      * | * | *       
;        v   v
;      * * * * *   * * * * *
; x--->*   *   --->*   * / *
;      * | * * *   * | * * *
;       wow          b
;
;
; z2-->* * * * *   * * * * *   * * * * *
;      *   *   --->*   *   --->*   * / *
;      * | * * *   * | * * *   * | * * *
;        |           a           b
;        |         * * * * *   * | * * *
;        --------->*   *   --->*   * / *
;                  * | * * *   * * * * *
;                   wow
