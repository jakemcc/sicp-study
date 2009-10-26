; Exercise 1.31

;{{{ Iterative version
(defn product [term a nxt b]
 (let [iter (fn [a result]
             (if (> a b)
                 result
                 (recur (nxt a)
                        (* result (term a)))))]
  (iter a 1)))

(defn factorial [n]
 (product identity 1 inc n))

(println (factorial 4))
(println (factorial 5))

; Iterative using (loop ...)
(defn product [term a nxt b]
 (loop [a a
        result 1]
   (if (> a b)
       result
       (recur (nxt a)
              (* result (term a))))))

(println (factorial 4))
(println (factorial 5))
;}}}
  


;{{{ Recursive version
(defn product [term a nxt b]
 (if (> a b)
     1
     (* (term a)
        (product term (nxt a) nxt b))))

(println (factorial 4))
(println (factorial 5))
;}}}

; vim: foldmethod=marker
