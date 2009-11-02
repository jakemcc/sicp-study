; Exercise 1.16

(defn square [x] (* x x))

(defn fast-expt [b n]
 (let [helper (fn [b n accum]
               (cond (= n 0) accum
                     (even? n) (recur (square b) (/ n 2) accum)
                     :else (recur b (- n 1) (* b accum))))]
  (helper b n 1)))



(fast-expt 2 3)
(fast-expt 2 4)
