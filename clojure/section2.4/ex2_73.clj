; Exercise 2.73

(use 'clojure.test)
;{{{Functions for manipulating operation table
(def *operation-table* (transient (hash-map)))

(defn put-op [op types proc]
  (assoc! *operation-table* (list op types) proc))

(defn get-op [op types]
  (get *operation-table* (list op types) '()))
;}}}

;{{{Needed functions for deriv example
(defn variable? [e] (symbol? e))

(defn same-variable? [v1 v2]
 (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [x n]
 (and (number? x) (= x n)))
;}}}

(defn operator [exp] (first exp))
(defn operands [exp] (rest exp))

(defn deriv [exp v]
 (cond (number? exp) 0
       (variable? exp) (if (same-variable? exp v) 1 0)
       :else ((get-op 'deriv (operator exp)) (operands exp)
                                             v)))
; A)
;
; This was turned into a data-directed dispatch function
; as the complex number example earlier.
;
; number? and same-variable? do not dispatched because
; there is no data to direct the dispatch.  They are simply
; predicates which look at the contents of the data.

; B) Write procedures for sums and products

; Followed book and am hiding functions inside another
; function.  Should probably use some namespaces...
(defn install-derivative-package []
 (letfn [(make-sum [a1 a2] (cond (=number? a1 0) a2
                                 (=number? a2 0) a1
                                 (and (number? a1) (number? a2)) (+ a1 a2)
                                 :else (list '+ a1 a2)))
         (addend [s] (second s))
         (augend [s] (first (rest (rest s))))
         
         (make-product [m1 m2] (cond (or (=number? m1 0) (=number? m2 0)) 0
                                     (=number? m1 1) m2
                                     (=number? m2 1) m1
                                     (and (number? m1) (number? m2)) (* m1 m2)
                                     :else (list '* m1 m2)))

         (multiplier [p] (second p))
         (multiplicand [p] (first (rest (rest p))))

         (deriv-sum [exp v]
                    (make-sum (deriv (addend exp) v)
                              (deriv (augend exp) v)))
         (deriv-product [exp v]
                        (make-sum
                          (make-product (multiplier exp)
                                        (deriv (multiplicand exp) v))
                          (make-product (deriv (multiplier exp) v)
                                        (multiplicand exp))))]
         (put-op 'deriv '+ deriv-sum)
         (put-op 'deriv '* deriv-product))
 'done)
(install-derivative-package)

(deftest can-deriv-using-functions-from-table
 (is (= 1 ((get-op 'deriv '+) '(+ x 3) 'x)))
 (is (= 3 ((get-op 'deriv '*) '(* x 3) 'x))))
(run-tests)

;(deriv '(+ x 3) 'x)
;(println (deriv '(* x y) 'x))
;(println (deriv '(* (* x y) (+ x 3)) 'x))

; c) I'm not going to do this.

; d) The way I've implemented get and put nothing would have to change.

; vim: set foldmethod=marker:
