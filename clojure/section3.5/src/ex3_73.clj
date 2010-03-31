; Exercise 3.73

(ns ex3-73
  (:use ex3-56))

(defn add-streams [s1 s2]
  (map + s1 s2))

(defn integral [integrand initial-value dt]
  ((fn inte []
     (lazy-seq
      (cons initial-value
            (add-streams
                 (scale-stream integrand dt)
                 (inte)))))))


(defn RC [R C dt]
  (fn [v0 i]
    (add-streams
     (scale-stream i R)
     (integral
      (scale-stream i (/ 1 C))
      v0
      dt))))
