; Exercise 3.82

(ns ex3-82
  (:use clojure.contrib.math))

(defn monte-carlo [experiment-stream passed failed]
  (letfn  [(nxt [passed failed]
             (lazy-seq
              (cons (/ passed (+ passed failed))
                    (monte-carlo
                     (rest experiment-stream) passed failed))))]
    (when (seq experiment-stream)
      (if (first experiment-stream)
        (nxt (inc passed) failed)
        (nxt passed (inc failed))))))

(defn random-in-range [low high]
  (+ low (rand (- high low))))

(defn rectangle-area [x1 x2 y1 y2]
  (abs (* (- x2 x1) (- y2 y1))))

(defn estimate-integral [p x1 x2 y1 y2]
  (map #(* %
           (rectangle-area x1 x2 y1 y2))
       (monte-carlo
        (map p
             (repeatedly #(random-in-range x1 x2))
             (repeatedly #(random-in-range x1 x2)))
        0 0)))

(defn estimate-pi-using-area-unit-circle []
 (letfn [(square [x] (* x x))
         (unit-circle-predicate
          [x y]
          (<= (+ (square x) (square y)) 1))]
   (estimate-integral
    unit-circle-predicate -1.0 1.0 -1.0 1.0)))


