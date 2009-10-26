; Odd tab foramatting in this file is to take advantage of 
; folding so all answers to individual problems can be
; seen without being distracted by answers to other problems.
;
;
;
; Exercise 1.42
    ; f and g are one-argument functions
    ; Define a procedure compose that implements composition of functions.
    ; Example: ((compose square inc) 6) -> 49

    (defn square [x] (* x x))

    (defn compose [f g]
     (fn [x] (f (g x))))

    ((compose square inc) 6)

    ; Could also use Clojure's comp
    (defn compose [f g]
     (comp f g))

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


; Exercise 1.44
    ; Write a function 'smooth which smoothes a function f by taking the average
    ; of f(x - dx), f(x), f(x + dx)


    (defn avg [x y z]
     (/ (+ x y z) 3))

    (def dx 0.01)
    (defn smooth [f]
     (fn [x] 
      (avg (f (- x dx)) 
           (f x)
           (f (+ x dx)))))

    ((smooth square) 6)


    ; Write a function which returns a procedure which smooths a provided procedure f
    ; n times

    (defn n-smooth [f n]
     (fn [x]
      (((repeated smooth n) f) x)))

    ((n-smooth square 2) 6)
