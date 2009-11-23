; Exercise 2.40

(use 'clojure.test)

(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(defn enumerate-interval [low high]
 (loop [cur high
        res nil]
  (if (< cur low)
      res
	  (recur (dec cur) (cons cur res)))))

(deftest test-enum-int
 (is (= '(1 2 3 4) (enumerate-interval 1 4)))
 (is (= nil (enumerate-interval 1 0)))
 (is (= '(1) (enumerate-interval 1 1))))

(defn flatmap [proc sq]
 (accumulate concat nil (map proc sq)))

(defn unique-pairs [n]
 (flatmap (fn [i] 
        (map (fn [j] (list i j)) 
	         (enumerate-interval 1 (dec i))))
        (enumerate-interval 1 n)))

(println (unique-pairs 5))

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

(defn prime-sum? [pair]
 (prime? (+ (first pair) (last pair))))

(defn make-pair-sum [pair]
 (list (first pair) (last pair) (+ (first pair) (last pair))))

(defn prime-sum-pairs [n]
 (map make-pair-sum
      (filter prime-sum?
	          (unique-pairs n))))

(println (prime-sum-pairs 5))

(run-tests)

; vim: foldmethod=marker
