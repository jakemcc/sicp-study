; Exercise 2.58
; code from previous examples

(use 'clojure.test)

(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [x n]
 (and (number? x) (= x n)))

(def sum?)
(def product?)
(def make-product)
(def make-sum)
(def addend)
(def augend)
(def multiplier)
(def multiplicand)

(defn make-exponentiation [b e]
 (cond (=number? e 0) 1
       (=number? e 1) b
       (and (number? b) (number? e)) (Math/pow b e)
       :else (list '** b e)))

(defn exponentiation? [exp]
  (and (seq? exp) (= '** (first exp))))

(defn base [exp]
  (second exp))

(defn exponent [exp]
  (first (rest (rest exp))))

(defn deriv [exp var]
 (cond (number? exp) 0
       (variable? exp) (if (same-variable? exp var) 1 0)
	   (sum? exp)
	     (make-sum (deriv (addend exp) var)
		           (deriv (augend exp) var))
	   (product? exp)
	     (make-sum
		   (make-product (multiplier exp)
			             (deriv (multiplicand exp) var))
		   (make-product (deriv (multiplier exp) var)
			             (multiplicand exp)))
       (exponentiation? exp)
         (make-product 
          (make-product (deriv (base exp) var) (exponent exp))
          (make-exponentiation var (dec (exponent exp))))
	   :else
	     (throw 'error)))

;
; A)
(defn make-sum [a1 a2]
 (cond (=number? a1 0) a2
       (=number? a2 0) a1
       (and (number? a1) (number? a2)) (+ a1 a2)
       :else (list a1 '+ a2)))

(defn make-product [m1 m2]
 (cond (or (=number? m1 0) (=number? m2 0)) 0
       (=number? m1 1) m2
       (=number? m2 1) m1
       (and (number? m1) (number? m2)) (* m1 m2)
       :else (list m1 '* m2)))

(defn sum? [x]
 (and (seq? x) (= (second x) '+)))
(defn addend [s]
 (first s))
(defn augend [s]
 (first (rest (rest s))))

(defn product? [x]
 (and (seq? x) (= (second x) '*)))
(defn multiplier [p]
 (first p))
(defn multiplicand [p]
 (first (rest (rest p))))

(newline)
(println '(deriv (x + (3 * (x + (y + 2)))) x))
(println (deriv '(x + (3 * (x + (y + 2)))) 'x))

; B)

; In part B it wants us to allow the following expression
; '(x + 3 * (x + y + 2))
; Actually follow an order of operation

(defn index-of [m coll]
 (loop [r coll
        i 0]
   (cond (empty? r) nil
         (= m (first r)) i
         :else (recur (rest r) (inc i)))))

(deftest index-of-works
 (is (= 1 (index-of '+ '(1 + 2))))
 (is (= nil (index-of '+ '(1 * 2)))))

(defn augend [x]
 (let [aug (drop (inc (index-of '+ x)) x)]
  (if (= 1 (count aug)) (first aug)
      aug)))

(deftest augend-works
 (is (= '(3 * (x + y + 2)) (augend '(x + 3 * (x + y + 2)))))
 (is (= 1 (augend '(3 * (x + y + 2) + 1))))
 (is (= 'x (augend '(2 + x)))))

(defn addend [x]
 (let [aug (take (index-of '+ x) x)]
  (if (= 1 (count aug)) (first aug)
      aug)))

(deftest addend-works
 (is (= 'x (addend '(x + 3 * (x + y + 2)))))
 (is (= '(3 * (x + y + 2)) (addend '(3 * (x + y + 2) + 1))))
 (is (= 2 (addend '(2 + x)))))

(deftest make-sum-works
 (is (= '(3 + (x * 4)) (make-sum 3 '(x * 4))))
 (is (= '(3 + x) (make-sum 3 'x))))

(deftest deriv-works
 (is (= 4 (deriv '(x + x + x + x) 'x)))
 (is (= 3 (deriv '(1 * x + x * 2) 'x)))
 (is (= 4 (deriv '(x + 3 * (x + y + 2)) 'x)))
 (is (= 4 (deriv '(3 * (x + y + 2) + x) 'x))))

(defn any? [t]
 (reduce (fn [x y] (or x y))
         false
         t))

(defn sum? [s]
 (any? (map #(= % '+) s)))

(deftest sum?-works
 (is (sum? '(3 * x + 5)))
 (is (not (sum? '(3 * (x + 5) * y))))
 (is (sum? '(3 + x))))

(run-tests)
