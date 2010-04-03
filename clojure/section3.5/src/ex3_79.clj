; Exercise 3.79

(ns ex3-79
  (:use ex3-77))

(defn solve-2nd [f dt y0 dy0]
  (letfn [(y [] (integral (delay (dy)) y0 dt))
          (dy [] (integral (delay (ddy)) dy0 dt))
          (ddy [] (map f (dy) (y)))]
    (y)))
