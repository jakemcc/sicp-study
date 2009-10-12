; Exercise 1.23
(ns exercise1.23)

(defn next-divisor [x]
 (if (= x 2)
     3
     (+ x 2)))

(defn divides? [a b]
 (= (mod b a) 0))

(defn square [x] (* x x))

(defn find-divisor [n test-divisor]
 (cond (> (square test-divisor) n) n
       (divides? test-divisor n) test-divisor
       :else (recur n (next-divisor test-divisor))))

(defn smallest-divisor [n]
 (find-divisor n 2))

; back to 1.22
(defn prime? [n]
 (= n (smallest-divisor n)))

(defn current-time []
 (System/nanoTime))

(defn report-prime [n elapsed-time]
 (println (str n " *** " elapsed-time " nanoseconds"))
 true)

(defn start-prime-test [n start-time]
 (if (prime? n)
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

(search-for-primes 10000000000)


; 1009 *** 35829 nanoseconds
; 1013 *** 35549 nanoseconds
; 1019 *** 35549 nanoseconds
; 10007 *** 103924 nanoseconds
; 10009 *** 104972 nanoseconds
; 10037 *** 104622 nanoseconds
; 100003 *** 322947 nanoseconds
; 100019 *** 322178 nanoseconds
; 100043 *** 324482 nanoseconds
; 1000003 *** 1141486 nanoseconds
; 1000033 *** 824825 nanoseconds
; 1000037 *** 789695 nanoseconds
; 
; Ratio betweeen 1.22 times and 1.23 times.
; First argument is 1.23 times
; Clojure=> (/ 35829 50705.0)
; 0.7066167044670151
; Clojure=> (/ 103924 159587)
; 103924/159587
; Clojure=> (/ 103924 159587.0)
; 0.6512059252946668
; Clojure=> (/ 322947 502368.0)
; 0.6428494649340722
; Clojure=> (/ 824825 934127.0) 
; 0.8829902143926897
;
; 10000000061 *** 63369500 nanoseconds
; 10000000061 *** 108381309 nanoseconds
; Clojure=> (/  63369500 108381309.0)
; 0.5846902993208912
;
; When we do the search for larger numbers it gets closer to 50% time.
; It isn't all the way to 50% which may be because of
; the additional function call and if check every time
; a new never is required.
