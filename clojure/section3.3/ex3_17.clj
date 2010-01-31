; Exercise 3.17
;
; Create a correct version of count-pairs from 3.16

(defn count-pairs [x]
  (let [visited (atom '())
        iter (fn iter [s]
               (cond (nil? s)) 0
                     (contains (first s) @visited) 0
                     :else (do
                             (swap! visited cons (first s))
                             (+
                               (iter (next s))
                               (iter (first s))
                               1)))]
     (iter x)))
