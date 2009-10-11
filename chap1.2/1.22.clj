; Exercise 1.22
(ns exercise1.22)

; Code taken from exercise 1.21
(defn divides? [a b]
 (= (mod b a) 0))

(defn square [x] (* x x))

(defn find-divisor [n test-divisor]
 (cond (> (square test-divisor) n) n
       (divides? test-divisor n) test-divisor
       :else (recur n (+ test-divisor 1))))

(defn smallest-divisor [n]
 (find-divisor n 2))

; back to 1.22
(defn prime? [n]
 (= n (smallest-divisor n)))

(defn current-time []
 (System/nanoTime))

(defn report-prime [n elapsed-time]
 (print n)
 (print " *** ")
 (print elapsed-time)
 (println " nanoseconds")
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
(search-for-primes 10000000)
(search-for-primes 100000000)
(search-for-primes 1000000000)
(search-for-primes 10000000000)
(search-for-primes 1000000000000)
; 1000000000039 *** 1126992125 nanoseconds
; 1000000000061 *** 1137560220 nanoseconds
; 1000000000063 *** 1154209115 nanoseconds

; Helper functions for estimating runtime of numbers
; an order of magnitude higher
(defn avg [x y z] (/ (+ x y z) 3.0))
(defn estimated-time [x] (* x (Math/sqrt 10)))

; prime *** time to find
; 1009 *** 50705 nanoseconds
; 1013 *** 50775 nanoseconds
; 1019 *** 50775 nanoseconds
(estimated-time (avg 50705 50775 50775))
; -> 160490.8617163122
; 
;
; 10007 *** 159587 nanoseconds
; 10009 *** 160914 nanoseconds
; 10037 *** 159587 nanoseconds
(estimated-time (avg 159587 160914 159587))
; -> 506057.18577163905
;
; 100003 *** 502368 nanoseconds
; 100019 *** 523881 nanoseconds
; 100043 *** 432667 nanoseconds
(estimated-time (avg 502368 523881 432667))
; -> 1537832.4916207371
;
; 1000003 *** 934127 nanoseconds
; 1000033 *** 942159 nanoseconds
; 1000037 *** 929727 nanoseconds
(estimated-time (avg 934127 942159 929727))
; -> 2957797.408014018
;
;  The time when testing different size numbers
;  tends to match the estimated increase in cost.
;  It failed on the final step from 10000 to 1000000
