; Exercise 3.69

(ns ex3-69)

(defn pairs [s t]
  (lazy-seq
   (cons
    (list (first s) (first t))
    (interleave
     (map (fn [x] (list (first s) x))
          (next t))
     (pairs (next s) (next t))))))

(defn triples [s t u]
  (lazy-seq
   (cons
    (list (first s) (first t) (first u))
    (interleave
     (map (fn [x] (list* (first s) x))
          (next (pairs t u)))
     (triples (next s) (next t) (next u))))))

(defn square [x] (* x x))

(defn pythagorean-triples []
  (filter (fn [x]
            (let [i (first x), j (second x), k (nth x 2)]
              (= (+ (square i)
                    (square j))
                 (square k))))
          (triples (iterate inc 1)
                   (iterate inc 1)
                   (iterate inc 1))))
