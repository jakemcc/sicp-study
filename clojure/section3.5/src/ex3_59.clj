; Exercise 3.59

(ns ex3-59)

; Part A
(defn integrate-series [s]
  ((fn iter [strm n]
     (lazy-seq
      (when-let [s (seq strm)]
        (cons (/ (first s) n)
              (iter (rest s) (inc n))))))
   s 1))

; Part b
(def exp-series (lazy-seq (cons 1
                                (integrate-series exp-series))))

(declare sine-series cosine-series)

(defn scale-series [s n]
  (map #(* % n) s))

(def cosine-series
     (lazy-seq (cons 1 (scale-series (integrate-series sine-series)
                                     -1))))

(def sine-series
     (lazy-seq (cons 0 (integrate-series cosine-series))))
