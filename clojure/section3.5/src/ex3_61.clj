
(ns ex3-61
  (:use ex3-60))


(defn invert-unit-series [s]
  (lazy-seq
   (cons 1
         (scale-series
          (mul-series
           (invert-unit-series s)
           (rest s))
          -1))))



