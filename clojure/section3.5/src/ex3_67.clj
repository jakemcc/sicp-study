; Exercise 3.67

(ns ex3-67)

(defn pairs [s t]
  (lazy-seq
   (cons
    (list (first s) (first t))
    (interleave
     (map (fn [x] (list (first s) x))
          (next t))
     (interleave
      (map (fn [x] (list x (first t)))
           (next s))
      (pairs (next s) (next t)))))))

(def integers (iterate inc 1))
