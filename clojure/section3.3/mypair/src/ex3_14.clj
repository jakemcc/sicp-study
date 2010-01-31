; Exercise 3.14
;
; The following procedure is a mystery. What does it do?

(defn mystery [x]
  (loop [x x, y '()]
    (if (nil? x)
        y
        (let [temp (next x)]
          (set-cdr! x y)
          (recur temp x)))))

; The procedure mystery reverses the sequence x and returns it.
; It also modifies the passed in sequence to simply be a list
; of its first element

; w -->* * * * *   * * * * *   * * * * *   * * * * *
;      * d *   *-->* c *   *-->* b *   *-->* a *   *-->nil
;      * * * * *   * * * * *   * * * * *   * * * * *
;                                            ^
; v -----------------------------------------|
