; Exercise 1.42

; f and g are one-argument functions
; Define a procedure compose that implements composition of functions.
; Example: ((compose square inc) 6) -> 49


(defn square [x] (* x x))

(defn compose [f g]
 (fn [x] (f (g x))))

((compose square inc) 6)







; Exercise 1.43
; Write a function which takes a function f
; and a number n and returns a function which 
; applies f n times


(defn repeated [f n]
 (let [helper (fn [g n]
               (if (= n 0) g
                   (recur (fn [x] (f (g x))) (dec n))))]
  (helper f (dec n))))

((repeated square 1) 5)
((repeated square 2) 5)
((repeated square 3) 5)


