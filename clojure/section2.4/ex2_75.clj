; Exercise 2.75
;
; Implement the constructor make-from-mag-ang in message
; passing style.

(defn make-from-mag-ang [mag ang]
 (fn [op] (cond (= op 'real-part) (* mag (Math/cos ang))
                (= op 'imag-part) (* mag (Math/sin ang))
                (= op 'magnitude) mag
                (= op 'angle) ang
                :else (Error. (str "Unknown op -- MAKE-FROM-MAG-ANG " op)))))
