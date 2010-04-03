; Exercise 3.80

(ns ex3-80
  (:use ex3-77))

(defn add-streams [s1 s2]
  (map + s1 s2))
(defn scale-stream [s x]
  (map #(* x %) s))

(defn RLC [R L C dt]
  (fn [vC0 iL0]
   (letfn [(iL [] (integral (delay (diL)) iL0 dt))
           (diL [] (add-streams
                    (scale-stream (iL) (- (/ R L)))
                    (scale-stream (vC) (/ 1 L))))
           (vC [] (integral (delay (dvC)) vC0 dt))
           (dvC [] (scale-stream (iL) (/ -1 C)))]
     (list (vC) (iL)))))


