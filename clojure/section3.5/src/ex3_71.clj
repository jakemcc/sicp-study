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
     (if (or (nil? s))
       nil
       (let [w1 (sum-of-two-cubes (first s))
             w2 (sum-of-two-cubes (second s))]
         (println "(" (first s)
                  "," (second s)
                  ") = " w1 w2)
         (if (= w1 w2)
           (lazy-seq (cons w1 (find-next (next s))))
           (recur (next s))))))
   (sum-of-cubes-stream)))



