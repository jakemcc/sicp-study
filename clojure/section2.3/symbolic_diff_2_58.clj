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

; Was originally thinking that if sum? was changed to return true only if a sum
; is the next operation that should be done it would solve this problem.  Thinking
; about it more I'm not sure if this would.  Am hoping to have some discussion
; in study group about this.
;
; (defn sum? [x]
;  (and (seq? x) (= (second x) '+)))
; 
; (deftest sum?-works-with-operator-precedence
;  (is (= true (sum? '(2 + 3))))
;  (is (= false (sum? '(2 + 3 * 5)))))
; 
; (deftest product?-works-with-operator-precedence
;  (is (= true (product? '(2 * 3))))
;  (is (= true (product? '(2 + 3 * 5)))))
; 
; (run-tests)
