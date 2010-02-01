; Exercise 3.17
;
; Create a correct version of count-pairs from 3.16

(ns ex3_17
 (:use mypair))

(defn has? [x es]
 (some #(= % x) es))

(defn atom? [x]
 (= clojure.lang.Atom (type x)))

(defn count-pairs [x]
  (let 
   [visited (atom '())
    iter (fn iter [s]
           (cond (not (atom? s)) 0
                 (has? s @visited) 0
                 :else (do
                         (swap! visited #(cons s %))
                         (+ 1
                            (iter (cdr s))
                            (iter (car s))))))]
                            
     (iter x)))

