; Exercise 2.81

;*******************************************************
; I made no attempt at getting code to run, treating
; as a thought exercise
;*******************************************************

; a)
;   In the example given there is a generic 'exp function
;   which has been defined for 'scheme-numbers
;   but not for 'complex numbers.  This is important to note.
;
;   Louis has also added two coercions, one scheme-number->scheme-number
;   and complex->complex.
;
;   The first step that 'apply-generic tries to do is look up
;   a procedure to apply based off what types the inputs are.
;       - If it finds a procedure it then applys it.
;       - If it does not find a procedure then it attemps to convert
;       the arguments to each others types, and if the conversion works
;       calls 'apply-generic with the newly convereted argument.
;
;   With Louis added coercions when the generic 'exp procedure
;   is called with two 'complex numbers, it will then call 'apply-generic.
;   'apply-generic will fail looking up a specific 'exp function
;   for inputs of 'complex type (this operation has only been defined
;   for 'scheme-numbers).  'apply-generic will then look to see
;   if it can convert either 'complex number into the other arguments type
;   (in this case another 'complex number). 'apply-generic will see that
;   it can, will then perform the conversion and call itself with
;   the newly converted argument.  Then this loop will start over again.
;


; b)
;   Nothing needed to be done.  If there were not coercions defined
;   between to go from type-x->type-x then 'apply-generic would
;   work as expected
;
; c) Give an 'apply-generic that does not try to coerce
; if two arguments are the same type.

(defn apply-generic [op & args]
 (let [type-tags (map type-tag args)
       no-method-error #(Error. (str "No method for these types " (list op type-tags)))
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
            (cond (= type1 type2) (no-method-error)
                  t1->t2 (apply-generic op (t1->t2 a1) a2)
                  t2->t1 (apply-generic op a1 (t2->t1) a2)
                  :else (Error. (str "No method for these types " (list op type-tags)))))
          (no-method-error)))))

           
