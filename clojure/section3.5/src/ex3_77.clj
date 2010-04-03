; Exercise 3.77

(ns ex3-77
  (:use ex3-56))

(defn integral [delayed-integrand initial-value dt]
  (lazy-seq
   (cons initial-value
         (let [integrand (force delayed-integrand)]
           (if (nil? integrand)
             nil
             (integral
              (delay (rest integrand))
              (+ (* dt (first integrand))
                 initial-value)
              dt))))))

(defn solve [f y0 dt]
  (letfn [(y [] (integral (delay (dy)) y0 dt))
          (dy [] (map f (y)))]
    (y)))

(comment (defn integral [delayed-integrand initial-value dt]
   ((fn int []
      (lazy-seq
       (cons initial-value
             (let [integrand (force delayed-integrand)]
               (map + (scale-stream integrand dt)
                    (int)))))))))

