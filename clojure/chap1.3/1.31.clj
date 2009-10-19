; Exercise 1.31

(defn product [term a nxt b]
 (let [iter (fn [a result]
             (if (> a b)
                 result
                 (recur (nxt a)
                        (* result (term a)))))]
  (iter a 1)))

(defn factorial [n]
 (product identity 1 inc n))

(do println (factorial 4))
(do println (factorial 5))

