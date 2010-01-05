 ; File where all polynomial problems are going to reside

;{{{Functions for manipulating operation table
(def *operation-table* (transient (hash-map)))

(defn put-op [op types proc]
  (assoc! *operation-table* (list op types) proc))

(defn get-op [op types]
  (get *operation-table* (list op types) '()))
;}}}


(def add)
(def mul)
(def attach-tag)

; Having issues with just getting this setup :(
(defn install-polynomial-package []
 (let [the-empty-termlist '()]
  (letfn
   [(variable? [x] (symbol? x))
    (same-variable? [v1 v2]
     (and (variable? v1) (variable? v2) (= v1 v2)))
    (make-poly [variable term-list]
     (cons variable term-list))
    (variable [p] (first p))
    (term-list [p] (rest p))
    (adjoin-term [term term-list]
     (if (zero? (coeff term))
         term-list
         (cons term term-list)))
 
    (first-term [term-list] (first term-list))
    (rest-terms [term-list] (rest term-list))
    (empty-termlist? [term-list] (empty? term-list))
    (make-term [order coeff] (list order coeff))
    (order [term] (first term))
    (coeff [term] (second term))
 
    (add-terms [L1 L2]
     (cond (empty-termlist? L1) L2
           (empty-termlist? L2) L1
           :else
             (let [t1 (first-term L1) 
                   t2 (first-term L2)]
                (cond (> (order t1) (order t2)) 
                       (adjoin-term
                         t1 (add-terms (rest-terms L1) L2))
                      (< (order t1) (order t2))
                       (adjoin-term
                         t2 (add-terms L1 (rest-terms L2)))
                     :else
                      (adjoin-term
                       (make-term (order t1)
                        (add (coeff t1) (coeff t2)))
                       (add-terms 
                        (rest-terms L1)
                        (rest-terms L2)))))))
 
     (mul-terms [L1 L2]
      (if (empty-termlist? L1) (the-empty-termlist)
          (add-terms (mul-term-by-all-terms (first-term L1) L2)
           (mul-terms (rest-terms L1) L2))))
 
     (mul-term-by-all-terms [t1 L]
      (if (empty-termlist? L)
           (the-empty-termlist)
          (let [t2 (first-term L)]
            (adjoin-term
             (make-term (+ (order t1) (order t2))
              (mul (coeff t1) (coeff t2)))
             (mul-term-by-all-terms t1 (rest-terms L))))))
 
     (add-poly [p1 p2]
      (if (same-variable? (variable p1) (variable p2))
          (make-poly (variable p1) (add-terms (term-list p1) (term-list p2)))
          (Error. (str "Polys not in same var -- ADD-POLY " (list p1 p2)))))
 
     (mul-poly [p1 p2]
      (if (same-variable? (variable p1) (variable p2))
          (make-poly (variable p1) (mul-terms (term-list p1) (term-list p2)))
          (Error. (str "Polys not in same var -- MUL-POLY " (list p1 p2)))))
 
     ;; Interface to the outer world
     (tag [p] (attach-tag 'polynomial p))]
     (put-op 'add '(polynomial polynomial)
      (fn [p1 p2] (tag (add-poly p1 p2))))
     (put-op 'mul '(polynomial polynomial)
      (fn [p1 p2] (tag (mul-poly p1 p2))))
     (put-op 'make 'polynomial
      (fn [v terms] (tag (make-poly v terms))))
     'done)))
      
(defn make-polynomial [v terms]
 ((get 'make 'polynomial) v terms))

(install-polynomial-package)


; vim: set foldmethod=marker:
