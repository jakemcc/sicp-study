; Exercise 1.24

(defn square [x] (* x x))

(defn expmod [base exp m]
 (cond (= exp 0) 1
       (even? exp)
        (rem (square (expmod base (/ exp 2) m))
                     m)
       :else (rem (* base (expmod base (- exp 1) m))
                  m)))

(defn fermat-test [n]
 (let [try-it (fn [a]
               (= (expmod a n n) a))]
  (try-it (+ 1 (rand-int (- n 1))))))

(defn fast-prime? [n times]
 (cond (= times 0) true
       (fermat-test n) (recur n (- times 1))
       :else false))

(defn current-time []
 (System/nanoTime))

(defn report-prime [n elapsed-time]
 (println (str n " *** " elapsed-time " nanoseconds"))
 true)

(defn start-prime-test [n start-time]
 (if (fast-prime? n 5)
     (report-prime n (- (current-time) start-time))))
     false

(defn timed-prime-test [n]
 (start-prime-test n (current-time)))

(defn search-for-primes [lower-bound]
 (let [consecutive-odd-search 
        (fn [x]
         (if (timed-prime-test x)
              x
              (recur (+ x 2))))
      find-three-primes 
        (fn [x] 
         (consecutive-odd-search 
          (+ 2 (consecutive-odd-search 
           (+ 2 (consecutive-odd-search x))))))]
      (if (odd? lower-bound)
         (find-three-primes lower-bound)
         (find-three-primes (inc lower-bound)))))

(timed-prime-test 199)
(timed-prime-test 199)
(timed-prime-test 199)
(println "Real tests")
(search-for-primes 1000)
(search-for-primes 10000)
(search-for-primes 100000)
(search-for-primes 1000000)
(search-for-primes 10000000)
(search-for-primes 100000000)
(search-for-primes 1000000000)
(search-for-primes 10000000000)
(search-for-primes 1000000000000)

; Q: How would you expect time needed to test primes near 
; 1,000 to compare to time needed for primes near 1,000,000?
; A: Because of the log n growth I would expect times to be
; about double for testing primes at 1,000,000 compared to 1,000.
; The data actually shows them as about the same size.  I'd guess
; that is because of modern day processer speed.
;
;
; For a similar 1000 times difference lets comare 1,000,000 and 
; 1,000,000,000
; near 1E9: 260159
; near 1E6: 174044
; For this difference we see a difference closer to the expected.
;
;
;
;
; 1009 *** 155327 nanoseconds
; 1013 *** 163288 nanoseconds
; 1019 *** 170483 nanoseconds
; 10007 *** 221187 nanoseconds
; 10009 *** 190946 nanoseconds
; 10037 *** 195416 nanoseconds
; 100003 *** 222514 nanoseconds
; 100019 *** 234877 nanoseconds
; 100043 *** 231105 nanoseconds
; 1000003 *** 174044 nanoseconds
; 1000033 *** 231245 nanoseconds
; 1000037 *** 167688 nanoseconds
; 10000019 *** 191225 nanoseconds
; 10000079 *** 200096 nanoseconds
; 10000103 *** 200654 nanoseconds
; 100000007 *** 1309315 nanoseconds
; 100000037 *** 224819 nanoseconds
; 100000039 *** 233480 nanoseconds
; 1000000007 *** 245213 nanoseconds
; 1000000009 *** 245143 nanoseconds
; 1000000021 *** 260159 nanoseconds
; 10000000019 *** 7011087 nanoseconds
; 10000000033 *** 4354603 nanoseconds
; 10000000061 *** 1957232 nanoseconds
; 1000000000039 *** 1711810 nanoseconds
; 1000000000061 *** 769092 nanoseconds
; 1000000000063 *** 1747568 nanoseconds
