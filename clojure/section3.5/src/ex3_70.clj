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

4(defn pairs [s t]
  (lazy-seq
   (cons
    (list (first s) (first t))
    (interleave
     (map (fn [x] (list (first s) x))
          (next t))
     (pairs (next s) (next t))))))

(defn weighted-pairs [weight s t]
  (lazy-seq
   (cons
    (list (first s) (first t))
    (merge-weighted weight
                    (map (fn [x] (list (first s) x))
                         (next t))
                    (pairs (next s) (next t))))))

; Look in ../test/test_ex3_70 for parts being tested
; a)
(take 5 (weighted-pairs #(+ (first %) (second %)) (iterate inc 1) (iterate inc 1)))

; b)
(def not-divisible
     (filter #(not (or (zero? (rem % 2))
                       (zero? (rem % 3))
                       (zero? (rem % 5))))
             (iterate inc 1)))

(take 5 (weighted-pairs #(let [i (first %)
                               j (second %)]
                           (+ (* 2 i)
                              (* 3 j)
                              (* 5 i j)))
                        not-divisible
                        not-divisible))

