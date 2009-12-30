; Exercise 3.82

; predef some functions just to be get ride of errors
(def get-coercion)
(def type-tag)
(def contents)


; This solution is flawed.  It only trys to convert to the FIRST
; type to which all arguments can be coerced. There is a possibility
; that an operation might not be defined for that FIRST type
; and there may be a later type which all args can be converted to
; where the operation is defined.

(defn all-can-convert-to? [target types]
 "Checks to see if all types can be converted to target type"
 (every? true? 
         ((map (fn [t] (or (= t target)
                       (get-coercion t target))))
         types)))

(defn find-a-common-type [types]
 "Takes a list of types and finds a single type they can
 all be converted into"
 (letfn [(first-true [bs ts]
          (cond (zero? (count bs)) nil
                (true? (first bs)) (first ts)
                :else (recur (rest bs) (rest ts))))]
   (map (fn [target] (all-can-convert-to? target types))
        types)))

(defn convert-all-to [desired-type args]
 "Converts all args to desired type"
 (map (fn [a]
       (let [current-type (type-tag a)]
        (if (= desired-type current-type) 
            a
            ((get-coercion current-type desired-type) a))))
      args))

(defn apply-generic [op & args]
 (let [type-tags (map type-tag args)
       no-method-error #(Error. (str "No method for these types " (list op type-tags)))
       proc (get op type-tags)]
  (if proc
      (apply proc (map contents args)) 
      (let [common-type (find-a-common-type type-tags)]
       (if common-type
           (apply
            (apply-generic 
             (list* op 
                   (convert-all-to common-type args)))))
           (no-method-error)))))

; Question: Give an example of when this (and the previous 2 arg version) fail
;
; Neither this nor the two argument version take into account if there is
; a type to which all arguments can be converted to but is not a
; type of the arguments.
;
; An two argument example.
; arg1 type is square
; arg2 type is circle
; operation is (sum-areas)
;
; A circle can't be converted to a square and a square cannot be converted to
; a circle.  They could both be converted to shape. sum-areas could be defined
; for shapes
