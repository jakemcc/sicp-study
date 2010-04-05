; Exercise 3.71

(ns ex3-71
  (:use ex3-70))

(defn cube [x] (* x x x))

(defn sum-of-cubes-pair [[x y]]
  (+ (cube x) (cube y)))

(defn sum-of-cubes-stream []
  (weighted-pairs sum-of-cubes-pair
                  (iterate inc 1)
                  (iterate inc 1)))

(defn ramanujan-numbers []
  ((fn find-next [s]
     (lazy-seq
      (let [w1 (sum-of-cubes-pair (first s))
	    w2 (sum-of-cubes-pair (second s))]
	(if (= w1 w2)
	  (cons w1 (find-next (next s)))
	  (find-next (next s))))))
   (sum-of-cubes-stream)))




