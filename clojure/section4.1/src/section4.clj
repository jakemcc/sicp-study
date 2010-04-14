
(ns section4)

; just going to try to do some of the coding things in here


(defn my-eval [exp env]
  (cond (self-evaluating? exp) exp
        (variable? exp) (lookup-variable-value exp env)
        (quoted? exp) (text-of-quotation exp)
        (assignment? exp) (eval-assignment exp env)
        (definition? exp) (eval-definition exp env)
        (if? exp) (eval-if exp env)
        (lambda? exp)
          (make-procedure (lamda-parameters exp)
                          (lambda-body exp)
                          env)
        (begin? exp)
          (eval-sequence (begin-actions exp) env)
        (cond? exp) (my-eval (cond->if exp) env)
        (application? exp)
          (my-apply (my-eval (operator exp) env)
                    (list-of-values (operands exp) env))
        :else (Error. (str "Unknown expression type -- EVAL" exp))))

(defn my-apply [procedure arguments]
  (cond (primitive-procedure? procedure)
          (apply-primitive-procedure procedure arguments)
        (compound-procedure? procedure)
          (eval-sequence
           (procedure-body procedure)
           (extend-environment
            (procedure-parameters procedure)
            arguments
            (procedure-environment procedure)))
        :else (Error. (str "Unknown procedure type -- APPLY" procedure))))

(declare no-operands?
         first-operand
         rest-operands)

(defn list-of-values [exps env]
  (if (no-operands? exps)
    '()
    (cons (my-eval (first-operand exps) env)
          (list-of-values (rest-operands exps) env))))

(declare if-predicate if-alternative if-consequent)

(defn eval-if [exp env]
  (if (true? (my-eval (if-predicate exp) env))
      (my-eval (if-consequent exp) env)
      (my-eval (if-alternative exp) env)))

(declare last-exp? first-exp rest-exps)

(defn eval-sequence [exps env]
  (cond (last-exp? exps) (eval (first-exp exps) env)
        :else (do (my-eval (first-exp exps) env)
                  (eval-sequence (rest-exps exps) env))))

(declare set-variable-value! assignment-variable assignment-value)
(defn eval-assignment [exp env]
  (set-variable-value! (assignment-variable exp)
                       (my-eval (assignment-value exp) env)
                       env)
  'ok)

(declare define-variable! definition-variable definition-value)
(defn eval-definition [exp env]
  (define-variable! (definition-variable exp)
    (my-eval (definition-value exp) env)
    env)
  'ok)

(defn self-evaluating? [exp] 
  (cond (number? exp) true
        (string? exp) true
        :else false))

(defn variable? [exp] (symbol? exp))


(defn tagged-list? [exp tag]
  (if (seq? exp)
    (= (first exp) tag)
    false))

(defn quoted? [exp]
  (tagged-list? exp 'quote))

(defn assignment? [exp]
  (tagged-list? exp 'set!))
(defn assignment-variable [exp] (second exp))
(defn assignment-value [exp] (nth exp 2))

(defn definition? [exp]
  (tagged-list? exp 'define))

(defn definition-variable [exp]
  (if (symbol? (second exp))
    (second exp)
    (first (first (rest exp)))))

(declare make-lambda)

(defn definition-value [exp]
  (if (symbol? (second exp))
    (nth exp 2)
    (make-lambda (rest (first (rest exp))) ; formal parameters
                 (rest (rest exp)))))          ; body

(defn lambda? [exp] (tagged-list? exp 'lambda))
(defn lambda-parameters [exp] (second exp))
(defn lambda-body [exp] (rest (rest exp)))

(defn make-lambda [parameters body]
  (cons 'lambda (cons parameters body)))

(defn car [x] (first x))
(defn cdr [x] (next x))
(defn cadr [x] (second x))
(defn caddr [x] (first (next (next x))))
(defn cdddr [x] (next (next (next x))))
(defn caddr [x] (first (next (next x))))
(defn cadddr [x] (first (next (next (next x)))))
(defn null? [x] (nil? x))

(defn if? [exp] (tagged-list? exp 'if))
(defn if-predicate [exp] (cadr exp))
(defn if-consequent [exp] (caddr exp))
(defn if-alternative [exp]
  (if (not (nil? (cdddr exp)))
    (cadddr exp)
    'false))

(defn make-if [predicate consequent alternative]
  (list 'if predicate consequent alternative))


(defn begin? [exp] (tagged-list? exp 'begin))
(defn begin-actions [exp] (cdr exp))
(defn last-exp? [xs] (null? (cdr xs)))
(defn first-exp [xs] (car xs))
(defn rest-exps [xs] (cdr xs))

(defn make-begin [xs] (cons 'begin xs))
(defn sequence->exp [xs]
  (cond (null? xs) xs
        (last-exp? xs) (first-exp xs)
        :else (make-begin xs)))

(defn pair? [x] (seq? x))

(defn application? [exp] (pair? exp))
(defn operator [exp] (car exp))
(defn operands [exp] (cdr exp))
(defn no-operands? [ops] (null? ops))
(defn first-operand [ops] (car ops))
(defn rest-operands [ops] (cdr ops))

(declare expand-clauses)
(defn cond? [exp] (tagged-list? exp 'cond))
(defn cond-clauses [exp] (cdr exp))
(defn cond-predicate [clause] (car clause))
(defn cond-else-clause? [clause]
  (= (cond-predicate clause) 'else))
(defn cond-actions [clause] (cdr clause))
(defn cond->if [exp]
  (expand-clauses (cond-clauses exp)))

(defn expand-clauses [clauses]
  (if (null? clauses)
    'false                              ; no else clause
    (let [first-clause (car clauses)
          rest-clauses (cdr clauses)]
      (if (cond-else-clause? first-clause)
        (if (null? rest-clauses)
          (sequence->exp (cond-actions first-clause))
          (Error. (str  "ELSE clause isn't last -- COND->IF"
                        clauses)))
        (make-if (cond-predicate first-clause)
                 (sequence->exp (cond-actions first-clause))
                 (expand-clauses rest-clauses))))))

(defn make-procedure [parameters body env]
  (list 'procedure parameters body env))
(defn compound-procedure? [p]
  (tagged-list? p 'procedure))
(defn procedure-parameters [p] (cadr p))
(defn procedure-body [p] (caddr p))
(defn procedure-environment [p] (cadddr p))


(defn enclosing-environment [env] (cdr env))
(defn first-frame [env] (car env))
(def the-empty-environment '())

; I think I may have to change how environments work since
; set-car! and set-cdr! might not work
(declare set-car! set-cdr!)
(defn make-frame [variables values]
  (cons variables values))
(defn frame-variables [frame] 0(car frame))
(defn frame-values [frame] (cdr frame))
(defn add-binding-to-frame! [var val frame]
  (set-car! frame (cons var (car frame)))
  (set-cdr! frame (cons val (cdr frame))))


(defn extend-environment [vars vals base-env]
  (if (= (count vars) (count vals))
    (cons (make-frame vars vals) base-env)
    (if (< (count vars) (count vals))
      (Error. (str "Too many arguments supplied" vars vals))
      (Error. (str "Too few arguments supplied" vars vals)))))


(defn analyze [exp]
  (cond (self-evaluating? exp) 
        (analyze-self-evaluating exp)
        (quoted? exp) (analyze-quoted exp)
        (variable? exp) (analyze-variable exp)
        (assignment? exp) (analyze-assignment exp)
        (definition? exp) (analyze-definition exp)
        (if? exp) (analyze-if exp)
        (lambda? exp) (analyze-lambda exp)
        (begin? exp) (analyze-sequence (begin-actions exp))
        (cond? exp) (analyze (cond->if exp))
        (application? exp) (analyze-application exp)
        :else
        (Error. (str "Unknown expression type -- ANALYZE" exp))))