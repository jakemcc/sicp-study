; Exercise 2.84

; Going to use only two argument version of apply-generic
; for simplicity

; defs to get it to not give erros when parsing.
(def type-tag)
(def contents)
(def get-coercion)

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
      (apply proc (map contents args)) 
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
