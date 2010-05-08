; Exercise 4.21

(ns ex4-21)

(defn factorial [n]
  ((fn [fact]
     (fact fact n))
   (fn [ft k]
     (if (= k 1)
       1
       (* k (ft ft (- k 1)))))))

; Part a
(defn fibonacci [n]
  (if (<= n 2)
    1
   ((fn [fib x y]
      (fib fib x y n))
    (fn [ft x y k]
      (if (= k 2)
        y
        (ft ft y (+ x y) (- k 1))))
    1 1)))

; Part b
(defn is-even? [x]
  ((fn [even? odd?]
     (even? even? odd? x))
   (fn [ev? od? n]
     (if (= n 0) true (od? ev? od? (- n 1))))
   (fn [ev? od? n]
     (if (= n 0) false (ev? ev? od? (- n 1))))))



