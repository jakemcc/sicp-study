(ns ex3_18
 (:use mypair))

(defn has? [x es]
 (some #(= % x) es))

(defn cycles? [s]
 (loop [curr s, visited '()]
  (cond (has? curr visited) true
        (= nil (cdr curr)) false
        :else (recur
                (cdr curr)
                (cons curr visited)))))
