; Exercise 1.33

(defn filtered-accumulate
 [include? combiner null-value term a nxt b]
 (let [iter (fn [a result]
             (if (> a b) result
                 (if (include? a)
                     (recur (nxt a)
                            (combiner result (term a)))
                     (recur (nxt a)
                            (combiner result null-value)))))]
  (iter a null-value)))

;{{{ Procedures pulled from section 1.2
(defn- divides? [a b]
 (= (mod b a) 0))

(defn- square [x] (* x x))

(defn- next-divisor [x]
 (if (= x 2)
     3
     (+ x 2)))
(defn- find-divisor [n test-divisor]
 (cond (> (square test-divisor) n) n
       (divides? test-divisor n) test-divisor
       :else (recur n (next-divisor test-divisor))))

(defn- smallest-divisor [n]
 (find-divisor n 2))

(defn- prime? [n]
 (= n (smallest-divisor n)))
; }}}

;{{{ a) write procedure using filtered-accumulate which returns sum of squares from a to b

(defn sum-squares-primes [a b]
 (filtered-accumulate prime? + 0 square a inc b))

; 1^2 + 2^2 3^2 + 5^2 = 39
(println (sum-squares-primes 1 5))

; }}}

;{{{ b) Write procedure that returns the product of all positive integers less
; than n that are relatively prime to n
(defn- gcd [a b]
 (if (= b 0)
     a
     (gcd b (rem a b))))

(defn product-integers-less-than-n-and-relatively-prime-n [n]
 (filtered-accumulate
    (fn [i] (= (gcd i n) 1))
    *
    1
    identity
    1
    inc
    (dec n)))

(product-integers-less-than-n-and-relatively-prime-n 10)

; }}}
; vim: foldmethod=marker
