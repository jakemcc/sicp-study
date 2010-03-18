; Exercise 3.57


; Example from book in Scheme
;(define fibs
;  (cons-stream 0
;               (cons-stream 1
;                            (add-streams (stream-cdr fibs)
;                                         fibs))))

(defn add-streams [s1 s2]
  (map + s1 s2))

(def fibs
     (lazy-seq (cons 0
                     (lazy-seq (cons 1 (add-streams (rest fibs)
                                                    fibs))))))


; With the caching optimization talked about in the book
; we have done a n-2 addtions where n is the nth fib number
; we are calculating. So to calculated the 5th fib number
; we have done 3 additions.

; If we don't cache then the call to (rest fibs) will always have already
; calculated the next number the recursive call to fibs needs. Since it
; isn't cached then the recursive call to fibs repeats this work.
