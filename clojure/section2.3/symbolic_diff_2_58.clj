; Exercise 2.58
; code from previous examples

(use 'clojure.test)

(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [x n]
 (and (number? x) (= x n)))

(declare sum? 
         product?
         make-product
         make-sum
         addend
         augend
         multiplier
         multiplicand)

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

(println "Exercise 2.58, A")
(println '(deriv (x + (3 * (x + (y + 2)))) x))
(println (deriv '(x + (3 * (x + (y + 2)))) 'x))

; B)

; In part B it wants us to allow the following expression
; '(x + 3 * (x + y + 2))
; and have it follow an order of operation

(defn index-of [m coll]
 (loop [r coll
        i 0]
   (cond (empty? r) nil
         (= m (first r)) i
         :else (recur (rest r) (inc i)))))

(defn augend [x]
 (let [aug (drop (inc (index-of '+ x)) x)]
  (if (= 1 (count aug)) (first aug)
      aug)))

(defn addend [x]
 (let [aug (take (index-of '+ x) x)]
  (if (= 1 (count aug)) (first aug)
      aug)))

(defn any? [t]
 (reduce (fn [x y] (or x y))
         false
         t))

(defn sum? [s]
 (any? (map #(= % '+) s)))

(deftest index-of-should-find-element
 (is (= 1 (index-of '+ '(1 + 2)))))
(deftest index-of-should-return-nil-if-elem-not-found
 (is (= nil (index-of '+ '(1 * 2)))))

(deftest augend-should-return-entire-part-after-first-upper-level-+
 (is (= '(3 * (x + y + 2)) (augend '(x + 3 * (x + y + 2))))))
(deftest augend-should-return-single-element-after-first-upper-level-+
 (is (= 1 (augend '(3 * (x + y + 2) + 1)))))
(deftest augend-should-handle-simple-addition
 (is (= 'x (augend '(2 + x)))))

(deftest addend-should-return-single-element-before-first-upper-level-+
 (is (= 'x (addend '(x + 3 * (x + y + 2))))))
(deftest addend-should-return-entire-part-before-first-upper-level-+
 (is (= '(3 * (x + y + 2)) (addend '(3 * (x + y + 2) + 1)))))
(deftest addend-should-handle-simple-addition
 (is (= 2 (addend '(2 + x)))))

(deftest make-sum-should-handle-complex-expression-as-second-argument
 (is (= '(3 + (x * 4)) (make-sum 3 '(x * 4)))))
(deftest make-sum-should-handle-simple-experessions-as-arguments
 (is (= '(3 + x) (make-sum 3 'x))))

(deftest deriv-should-handle-multiple-additions-in-a-row
 (is (= 4 (deriv '(x + x + x + x) 'x))))
(deftest deriv-should-handle-situation-with-no-nesting
 (is (= 3 (deriv '(1 * x + x * 2) 'x))))
(deftest deriv-should-handle-starting-with-+-operation-with-nested-+-operations
 (is (= 4 (deriv '(x + 3 * (x + y + 2)) 'x))))
(deftest deriv-should-handle-final-operation-in-list-being-+-with-nested-+-operations
 (is (= 4 (deriv '(3 * (x + y + 2) + x) 'x))))

(deftest sum?-should-detect-sum-which-isnt-first-operator
 (is (sum? '(3 * x + 5))))
(deftest sum?-should-ignore-+-that-isnt-on-outmost-level
 (is (not (sum? '(3 * (x + 5) * y)))))
(deftest sum?-should-handle-simple-case
 (is (sum? '(3 + x))))

(run-tests)
