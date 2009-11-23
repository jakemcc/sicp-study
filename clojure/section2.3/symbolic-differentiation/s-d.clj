; File in which I do the Symbol-diffrentiation examples

(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn make-sum [a1 a2]
 (cond 
       (and (number? a1) (number? a2)) (+ a1 a2)
       
 (list '+ a1 a2))

(defn make-product [m1 m2]
 (list '* m1 m2))

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
