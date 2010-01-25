; Exercise 2.73 done with multimethods

(use 'clojure.test)

;{{{Needed functions for deriv example
(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [x n]
 (and (number? x) (= x n)))
;}}}

;{{{ Constructor and selectors for sums
(defn make-sum [a1 a2] 
 (cond (=number? a1 0) a2
  (=number? a2 0) a1
  (and (number? a1) (number? a2)) (+ a1 a2)
  :else (list '+ a1 a2)))

(defn addend [s] (second s))

(defn augend [s] (first (rest (rest s))))
;}}}

;{{{ Constructors and selectors for products
(defn make-product [m1 m2] 
 (cond (or (=number? m1 0) (=number? m2 0)) 0
       (=number? m1 1) m2
       (=number? m2 1) m1
       (and (number? m1) (number? m2)) (* m1 m2)
       :else (list '* m1 m2)))

(defn multiplier [p] (second p))

(defn multiplicand [p] (first (rest (rest p))))
;}}}

(defn operator [exp] (first exp))
(defn operands [exp] (rest exp))

; Define multimethod for taking derivatives
(defmulti do-deriv (fn [exp v] (operator exp)))

(defmethod do-deriv '+
 [exp v]
 (make-sum (deriv (addend exp) v)
  (deriv (augend exp) v)))

(defmethod do-deriv '* 
 [exp v]
 (make-sum
  (make-product (multiplier exp)
                (deriv (multiplicand exp) v))
  (make-product (deriv (multiplier exp) v)
                (multiplicand exp))))

(defn deriv [exp v]
 (cond (number? exp) 0
       (variable? exp) (if (same-variable? exp v) 1 0)
       :else (do-deriv exp v)))

(deftest can-deriv-using-functions-from-table
 (is (= 1 (do-deriv '(+ x 3) 'x)))
 (is (= 3 (do-deriv '(* x 3) 'x))))

(deftest can-call-deriv-directly
 (is (= 1  (deriv '(+ x 3) 'x)))
 (is (= 'y (deriv '(* x y) 'x)))
 (is (= 4  (deriv '(+ (* x 3) x) 'x))))

(run-tests)

; vim: set foldmethod=marker:
