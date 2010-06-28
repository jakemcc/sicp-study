(ns ex4-35)

(defn an-integer-between [n z]
  (if (> n z)
    (amb)
    (amb n (an-integer-between (inc n) z))))

