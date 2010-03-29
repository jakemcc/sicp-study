; Exercise 3.70

(ns ex3-70)

(defn merge-weighted [weight s1 s2]
  (cond (nil? s1) s2
        (nil? s2) s1
        :else
        (let [x1 (first s1), x2 (first s2)]
          (if (<= (weight x1) (weight x2))
            (lazy-seq
             (cons x1 (merge-weighted weight (next s1) s2)))
            (lazy-seq
             (cons x2 (merge-weighted weight s1 (next s2))))))))

