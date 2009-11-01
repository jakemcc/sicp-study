; Exercise 2.23

; for-each is a procedure which takes a list and procedure
; as arguments.  The procedure is applied for each element
; in the list.  Nothing is done with return values.
;
; Implement for-each

; Not sure if I like how this handles emtpy lists
(defn for-each [p l]
 (letfn [(iter [x xs]
             (p x)
             (if (empty? xs) true
                 (recur (first xs) (rest xs))))]
  (iter (first l) (rest l))))

(for-each (fn [x] (println x))
          (list 57 321 88))

(for-each (fn [x] (println x))
          (list))
