; Exercise 1.35
(defn average [x y]
 (/ (+ x y) 2))

(defn abs [x] (Math/abs x))

(def tolerance 0.00001)
(defn close-enough? [v1 v2] 
 (< (abs (- v1 v2)) tolerance))

(defn fixed-point [f first-guess]
 (let [attempt (fn [guess]
          (let [next (f guess)]
                 (println guess)
                 (if (close-enough? guess next)
                      next
                      (recur next))))]
       (attempt first-guess)))

(defn calculate-golden-ratio
 []
 (fixed-point (fn [x] (+ 1 (/ 1 x)))
              1.0))

(calculate-golden-ratio)
; 1.0
; 2.0
; 1.5
; 1.6666666666666665
; 1.6
; 1.625
; 1.6153846153846154
; 1.619047619047619
; 1.6176470588235294
; 1.6181818181818182
; 1.6179775280898876
; 1.6180555555555556  
; 1.6180257510729614
; 1.6180371352785146
; 1.6180327868852458 <- Close to golden ratio
