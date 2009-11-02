; Exercise 1.46

(defn iterative-improve [good? improve-guess]
 (fn [guess]
  (loop [previous-guess guess
         current-guess (improve-guess guess)]
   (if (good? previous-guess current-guess)
       current-guess
       (recur current-guess (improve-guess current-guess))))))

; Redefine sqrt from section 1.1.7 using iterative-improve
(defn average [x y]
    (/ (+ x y) 2))

(defn sqrt [n]
 ((iterative-improve
    (fn [g1 g2] (< (Math/abs (- g1 g2)) 
                   (* 0.0001 g2)))
    (fn [guess] (average guess (/ n guess))))
  1.0))

(println (sqrt 4))
(println (sqrt 16))

; Redefine fixed-point of section 1.3.3 to use iterative-improve
(defn fixed-point [f first-guess]
 ((iterative-improve
   (fn [g1 g2] (< (Math/abs (- g1 g2)) 0.00001))
   f)
  first-guess))

; Procedure to show that fixed-point works
(defn calculate-golden-ratio
 []
 (fixed-point (fn [x] (+ 1 (/ 1 x)))
              1.0))
(println (calculate-golden-ratio))
