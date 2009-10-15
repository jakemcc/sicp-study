; Exercise 1.27
; Not working yet

(defn expmod [base exp m]
 (cond (= exp 0) 1
       (even? exp)
        (rem (square (expmod base (/ exp 2) m))
                     m)
       :else (rem (* base (expmod base (- exp 1) m))
                  m)))

(defn carmichael? [n]
 (let [try-it (fn [a]
               (= (expmod a n n) a))
       all-under (fn [x]
                  (cond (= x 1) true 
                        (try-it x) (recur (dec x))
                        :else false ))]
   (all-under (dec n))))

(carmichael? 199) ;  false
(carmichael? 561) ;  true
(carmichael? 1105) ; true
(carmichael? 1729) ; true
(carmichael? 2465) ; true


 
