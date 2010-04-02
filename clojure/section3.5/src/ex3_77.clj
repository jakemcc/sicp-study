; Exercise 3.77

(ns ex3-77)


; Note that I don't do a (delay (next integrand)) when calling
; integral in this function. In Clojure if what force is working
; on isn't a delay then it simply returns its input. Hence
; there is no reason to delay the inputs to integral past the
; first time as it is only the first value we are concerned with delaying
(defn integral [delayed-integrand initial-value dt]
  (lazy-seq
   (cons initial-value
         (let [integrand (force delayed-integrand)]
           (if (nil? integrand)
             nil
             (integral (next integrand)
                       (+ (* dt (first integrand))
                          initial-value)
                       dt))))))
