
(ns ex3-60)

(defn scale-series [s n]
  (map #(* % n) s))

(defn add-streams [s1 s2]
  (map + s1 s2))

(defn mul-series [s1 s2]
  (lazy-seq
   (cons (* (first s1) (first s2))
    (add-streams
     (scale-series (rest s2) (first s1))
     (mul-series (rest s1) s2)))))


