
(ns section4-test
  (:use clojure.test
        section4))

(use-fixtures :each (fn [f] (reset-global-environment) (f)))

(deftest test-self-eval
  (is (= 5 (interpret 5)))
  (is (= "hey" (interpret "hey"))))

(deftest test-expressions
  (are [x y] (= x y)
       3 (interpret '(+ 1 2))
       -14 (interpret '(* (- 10 3) (- 4 6)))
       8 (interpret '(* (/ 4 2) (- 6 4) (+ 1 1)))))

(deftest test-quoted
  (are [x y] (= x y)
       2             (interpret '(quote 2))
       'howdy        (interpret '(quote howdy))
       '(jake jim 2) (interpret '(quote (jake jim 2)))))

(deftest test-if
  (are [x] (true? x)
       (interpret '(if (= 1 1) true false))
       (interpret '(if (= 1 0) false true))))

(deftest test-cond
  (are [x] (true? x)
       (interpret '(cond ((= 1 2) false)
                         ((= 2 2) true)
                         ((= 2 3) false)))
       (interpret '(cond ((= 1 2) false)
                         ((= 2 3) false)
                         (else true)))))


(deftest test-or
  (is (interpret '(or 5 4 3)))
  (is (false? (interpret '(or false false)))))

(deftest test-and
  (is (true? (interpret '(and true true))))
  (is (false? (interpret '(and false true)))))

(deftest test-vars
  (interpret '(define twelve 12))
  (is (= 12 (interpret 'twelve)))
  (is (= 14 (interpret '(+ twelve 2))))
  (interpret '(define two 2))
  (is (= 2 (interpret 'two)))
  (is (= 14 (interpret '(+ two twelve))))
  (interpret '(set! twelve 9))
  (is (= 9 (interpret 'twelve))))

(deftest test-define
  (interpret
   '(define (ident a) a))
  (interpret '(define (sum a b) (+ a b)))
  (is (= 5 (interpret '(ident 5))))
  (is (= 10 (interpret '(sum 4 6))))
  (is (= 11 (interpret '(sum (ident 5) 6)))))

(deftest test-lambdas
  (is (= 10 (interpret '((lambda (a b) (+ a b)) 7 3)))))

(deftest test-recurive-function
  (interpret
   '(define (exp x y)
      (if (= y 1)
        x
        (exp (* x x) (- y 1)))))
  (is (= 25 (interpret '(exp 5 2)))))
