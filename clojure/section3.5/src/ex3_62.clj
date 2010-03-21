; Exericse 3.62

(ns ex3-62
  (:use ex3-60
        ex3-61
        [ex3-59 :only (cosine-series sine-series)]))

(defn div-series [top denom]
  (if (zero? (first denom)) (Error. "can't divide by zero"))
  (let [c (/ 1 (first denom))]
    (scale-series
     (mul-series top
                 (invert-unit-series (scale-series denom c)))
     c)))

(def tanget-series
     (div-series sine-series cosine-series))


