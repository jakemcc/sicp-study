; Exercise 3.78

(ns ex3-78
  (:use ex3-77))

(defn add-streams [s1 s2]
  (map + s1 s2))

(defn scale-stream [s x]
  (map #(* x %) s))

(defn solve-2nd [a b dt y0 dy0]
  (letfn [(y [] (integral (delay (dy))  y0 dt))
          (dy [] (integral (delay (ddy)) dy0 dt))
          (ddy [] (add-streams
                   (scale-stream (dy) a)
                   (scale-stream (y) b)))]
    (y)))