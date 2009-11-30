; File in which I do the Symbol-diffrentiation examples

(use 'clojure.test)

(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [x n]
 (and (number? x) (= x n)))

(defn make-sum [a1 a2]
 (cond (=number? a1 0) a2
       (=number? a2 0) a1
       (and (number? a1) (number? a2)) (+ a1 a2)
       :else (list '+ a1 a2)))

(defn make-product [m1 m2]
 (cond (or (=number? m1 0) (=number? m2 0)) 0
       (=number? m1 1) m2
       (=number? m2 1) m1
       (and (number? m1) (number? m2)) (* m1 m2)
       :else (list '* m1 m2)))

(defn sum? [x]
 (and (seq? x) (= (first x) '+)))

(defn addend [s]
 (second s))

(defn augend [s]
 (first (rest (rest s))))

(defn product? [x]
 (and (seq? x) (= (first x) '*)))

(defn multiplier [p]
 (second p))

(defn multiplicand [p]
 (first (rest (rest p))))

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
	   :else
	     (throw 'error)))

(println (deriv '(+ x 3) 'x))
(println (deriv '(* x y) 'x))
(println (deriv '(* (* x y) (+ x 3)) 'x))

; Exercise 2.56
; Add support for exponents

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

(newline)
(println "Exercise 2.56")
(println '(deriv '(** x 4) x))
(println (deriv '(** x 4) 'x))
(println '(deriv '(** (* 2 x) 4) x))
(println (deriv '(** (* 2 x) 4) 'x))

; Exercise 2.57
; To make easier I got rid of simplification
(newline)
(println  "Exercise 2.57" )

(defn make-sum [& args]
 (cons '+ args))
(defn addend [a]
 (second a))
(defn augend [a]
 (let [x (rest (rest a))]
   (if (= 1 (count x)) (first x)
       (cons '+ x))))

(defn make-product [& args]
 (cons '* args))
(defn multiplier [m]
 (second m))
(defn multiplicand [m]
 (let [x (rest (rest m))]
   (if (= 1 (count x)) (first x)
       (cons '* x))))

(deftest multiplicand-works
 (is (= '(* 2 4) (multiplicand '(* 1 2 4))))
 (is (= 4 (multiplicand '(* 2 4)))))

(deftest augend-works 
 (is (= '(+ 2 4) (augend '(+ 1 2 4))))
 (is (= 4 (augend '(+ 2 4)))))

(deftest can-construct-simple-sum
 (is (= '(+ x y) (make-sum 'x 'y))))
(deftest can-construct-longer-sum
 (is (= '(+ x y z) (make-sum 'x 'y 'z))))

(deftest can-construct-simple-product
 (is (= '(* x y) (make-product 'x 'y))))
(deftest can-construct-longer-product
 (is (= '(* x y z) (make-product 'x 'y 'z))))

(println '(deriv (* x y (+ x 3)) x))
(println (deriv '(* x y (+ x 3)) 'x))


(run-tests)

; Exercise 2.58
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
;
