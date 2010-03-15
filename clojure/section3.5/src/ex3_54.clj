; Exercise 3.54

(defn mul-streams [x y]
  (map * x y))

(defn integers-starting-from [n]
  (iterate inc n))

(def factorials
     (lazy-seq
       (cons 1 (mul-streams factorials (integers-starting-from 2)))))
