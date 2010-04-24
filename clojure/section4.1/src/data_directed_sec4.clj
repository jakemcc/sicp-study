
(ns data-directed-sec4
  (:use scheme-helpers
        environment))

(declare execute-application
         primitive-procedure-names
         primitive-procedure-objects)

(declare my-eval
         my-apply
         analyze)

(declare no-operands?
         first-operand
         rest-operands)

; Exercise 4.1
(defn list-of-values [exps env]
  (if (no-operands? exps)
    '()
    (let [left (my-eval (first-operand exps) env)
          right (list-of-values (rest-operands exps) env)]
      (cons left right))))
; Above function imposes a left to right ordering. If the
; assignments inside of let where switched it would be right
; to left

(declare if-predicate if-alternative if-consequent)

(defn tag [exp]
  (if (seq? exp)
    (first exp)
    (Error. (str "this shouldn't happen"))))

(defn eval-if [exp env]
  (if (true? (my-eval (if-predicate exp) env))
      (my-eval (if-consequent exp) env)
      (my-eval (if-alternative exp) env)))

(declare last-exp? first-exp rest-exps)

(defn eval-sequence [exps env]
  (cond (last-exp? exps) (my-eval (first-exp exps) env)
        :else (do (my-eval (first-exp exps) env)
                  (eval-sequence (rest-exps exps) env))))

(declare assignment-variable assignment-value)

(defn eval-assignment [exp env]
  (set-variable-value! (assignment-variable exp)
                       (my-eval (assignment-value exp) env)
                       env)
  'ok)

(declare definition-variable definition-value)

(defn eval-definition [exp env]
  (define-variable!
    (definition-variable exp)
    (my-eval (definition-value exp) env)
    env)
  'ok)

(defn self-evaluating? [exp] 
  (cond (number? exp) true
        (string? exp) true
        :else false))

(defn variable? [exp]
  (or (symbol? exp)
      (= 'true exp)
      (= 'false exp)))

(defn tagged-list? [exp tag]
  (if (seq? exp)
    (= (first exp) tag)
    false))

(defn text-of-quotation [exp] (cadr exp))

(defn assignment-variable [exp] (second exp))

(defn assignment-value [exp] (nth exp 2))

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

(defn lambda-parameters [exp] (second exp))

(defn lambda-body [exp] (rest (rest exp)))

(defn make-lambda [parameters body]
  (cons 'lambda (cons parameters body)))

(defn if-predicate [exp] (cadr exp))

(defn if-consequent [exp] (caddr exp))

(defn if-alternative [exp]
  (if (not (nil? (cdddr exp)))
    (cadddr exp)
    'false))

(defn make-if [predicate consequent alternative]
  (list 'if predicate consequent alternative))

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

(defn cond-clauses [exp] (cdr exp))

(defn cond-predicate [clause] (car clause))

(defn cond-else-clause? [clause]
  (= (cond-predicate clause) 'else))

(defn cond-actions [clause] (cdr clause))

(defn cond->if [exp]
  (expand-clauses (cond-clauses exp)))

(defn expand-clauses [clauses]
  (if (null? clauses)
    'false
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

(defn analyze-sequence [exps]
  (letfn [(sequentially [proc1 proc2]
                        (fn [env] (proc1 env) (proc2 env)))
          (lop [first-proc rest-procs]
               (if (null? rest-procs)
                 first-proc
                 (lop (sequentially first-proc (car rest-procs))
                      (cdr rest-procs))))]
    (let [procs (map analyze exps)]
      (if (null? procs)
        (Error. "Empty sequence -- ANALYZE"))
      (lop (car procs) (cdr procs)))))

(defn analyze-application [exp]
  (let [fproc (analyze (operator exp))
        aprocs (map analyze (operands exp))]
    (fn [env]
      (execute-application (fproc env)
                           (map (fn [aproc] (aproc env))
                                aprocs)))))

(defn analyze-self-evaluating [exp]
  (fn [env] exp))

(defn analyze-quoted [exp]
  (let [qval (text-of-quotation exp)]
    (fn [env] qval)))

(defn analyze-variable [exp]
  (fn [env] (lookup-variable-value exp env)))

(defn analyze-assignment [exp]
  (let [var (assignment-variable exp)
        vproc (analyze (assignment-value exp))]
    (fn [env]
      (set-variable-value! var (vproc env) env)
      'ok)))

(defn analyze-definition [exp]
  (let [var (definition-variable exp)
        vproc (analyze (definition-value exp))]
    (fn [env]
      (define-variable! var (vproc env) env)
      'ok)))

(defn analyze-if [exp]
  (let [pproc (analyze (if-predicate exp))
        cproc (analyze (if-consequent exp))
        aproc (analyze (if-alternative exp))]
    (fn [env]
      (if (true? (pproc env))
        (cproc env)
        (aproc env)))))

(defn analyze-lambda [exp]
  (let [vars (lambda-parameters exp)
        bproc (analyze-sequence (lambda-body exp))]
    (fn [env] (make-procedure vars bproc env))))

(def primitive-procedures
     (list (list 'car car)
           (list 'cdr cdr)
           (list 'cons cons)
           (list 'null? null?)
           (list '+ +)
           (list '- -)
           (list '* *)
           (list '/ /)
           (list '= =)
           (list '> >)
           (list '< <)
           (list 'and (fn [& xs] (reduce #(and %1 %2) true xs)))
           (list 'or (fn [& xs] (reduce #(or %1 %2) false xs)))))

(defn primitive-procedure-names []
  (map car primitive-procedures))

(defn primitive-procedure-objects []
  (map (fn [proc] (list 'primitive (cadr proc)))
       primitive-procedures))

(defn setup-environment []
  (let [initial-env
        (extend-environment (primitive-procedure-names)
                            (primitive-procedure-objects)
                            the-empty-environment)]
    (define-variable! 'true true initial-env)
    (define-variable! 'false false initial-env)
    initial-env))

(def the-global-environment (setup-environment))

(defn reset-global-environment []
  (def the-global-environment (setup-environment)))

(defn primitive-procedure? [proc]
  (tagged-list? proc 'primitive))

(defn primitive-implementation [proc] (cadr proc))

(defn apply-primitive-procedure [proc args]
  (apply (primitive-implementation proc) args))

(defn execute-application [proc args]
  (cond (primitive-procedure? proc)
          (apply-primitive-procedure proc args)
        (compound-procedure? proc)
          ((procedure-body proc)
           (extend-environment (procedure-parameters proc)
                               args
                               (procedure-environment proc)))
        :else
        (Error. (str
                 "Unknown procedure type -- EXECUTE-APPLICATION"
                 proc))))

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
        (Error. (str "Unknown expression type -- ANALYZE " exp))))

(defmulti eval-method (fn [exp env] (tag exp)))

; This method applys the function.
(defmethod eval-method :default [exp env]
  (my-apply (my-eval (operator exp) env)
            (list-of-values (operands exp) env)))

(defmethod eval-method 'quote [exp env]
  (text-of-quotation exp))

(defmethod eval-method 'set! [exp env]
  (eval-assignment exp env))

(defmethod eval-method 'define [exp env]
  (eval-definition exp env))

(defmethod eval-method 'lambda [exp env]
  (make-procedure (lambda-parameters exp)
                  (lambda-body exp)
                  env))

(defmethod eval-method 'if [exp env]
  (eval-if exp env))

(defmethod eval-method 'begin [exp env]
  (eval-sequence (begin-actions exp) env))

(defn my-eval [exp env]
  (cond (self-evaluating? exp) exp
        (variable? exp) (lookup-variable-value exp env)
        :else (eval-method exp env)))

(defmethod eval-method 'cond [exp env]
  (my-eval (cond->if exp) env))

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
        :else (Error. (str "Unknown procedure type -- APPLY " procedure))))

(defn interpret [exp]
  (my-eval exp the-global-environment))
