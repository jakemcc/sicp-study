; Exercise 2.85
;
; Write a generic 'drop (I called it drop-type) procedure which 
; simplifies numeric types to simpler types if possible
;
; Basically the opposite of 'raise

(use 'clojure.contrib.math)

(def type-tag)
(def contents)
(def get-coercion)
(def raise)
(defn put-op [x y z])
(def make-real)
(def make-rational)
(def make-scheme-number)
(def real-part)
(def imag-part)
(def numer)
(def denom)

; Functions for dropping types 
; complex -> real -> rational -> integer

(put-op 'project '(complex)
 (fn [x] (make-real (real-part x))))

; Not really sure what to do for this one
; and I don't feel like thinking too hard about it
; so skipping
;(put-op 'project '(real)
; (fn [x]
;  (make-rational)))

(put-op 'project '(rational)
 (fn [x] (make-scheme-number 
          (round (/ (numer x) 
                    (denom x))))))

; Calling drop-type since drop is keyword in Clojure
(defn drop-type [higher]
 "Takes numeric type higher and tries to lower it to a subtype"
 (let [project-func (get 'project (type-tag higher))]
  (if project-func 
      (let [lower (project-func higher)]
        (if (= lower (raise lower))
            (recur lower)
             higher))
      higher)))

(defn apply-generic [op & args]
 (letfn [(multi-raise [begin target]
          "recursivly tries to coerce begin to be same type as target"
          (let [begin-type (type-tag begin)
                target-type (type-tag target)]
            (cond (= begin-type target-type) begin
                  (get 'raise (list begin-type))
                    (multi-raise ((get 'raise (list begin-type)) begin target)))
                  :else nil))
         (no-method-error [tags] 
            (Error. (str "No method for these types " (list op tags))))]
 (let [type-tags (map type-tag args)
       proc (get op type-tags)]
  (if proc
      (drop-type (apply proc (map contents args))) ; Only line that needed to change to get types to be lowered
      (if (= (count args) 2)
          (let [type1 (first type-tags)
                type2 (second type-tags)
                a1 (first args)
                a2 (second args)
                t1->t2 (get-coercion type1 type2)
                t2->t1 (get-coercion type2 type1)]
            (cond (= type1 type2) (no-method-error type-tags)
                  (multi-raise a1 a2) (apply-generic op (multi-raise a1 a2) a2)
                  (multi-raise a2 a1) (apply-generic op a1 (multi-raise a2 a1))
                  :else (Error. (str "No method for these types " (list op type-tags)))))
          (no-method-error type-tags))))))
