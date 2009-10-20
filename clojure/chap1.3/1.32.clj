; Exercise 1.32

; Iterative solution
(defn accumulate
 [combiner null-value term a nxt b]
 (let [iter (fn [a result]
             (if (> a b) result
                 (recur (nxt a)
                        (combiner result (term a)))))]
  (iter a null-value)))

; Product defined using accumulate
(defn product [term a nxt b]
 (accumulate * 1 term a nxt b))

; Sum from 1.30 defined using accumulate
(defn sum [term a nxt b]
 (accumulate + 0 term a nxt b))

(defn cube [x] (* x x x))

(defn sum-cubes [a b]
 (sum cube a inc b))

; Compare to 1.30.  Same answer
(do println (sum-cubes 1 10))

(defn factorial [n]
 (product identity 1 inc n))

; Compare to 1.31
(do println (factorial 5))
   



; Recursive solution 
(defn accumulate
 [combiner null-value term a nxt b]
 (letfn [(iter [a]
             (if (> a b) null-value 
                 (combiner (term a) (iter (nxt a)))))]
  (iter a)))

(do println (sum-cubes 1 10))
(do println (factorial 5))
