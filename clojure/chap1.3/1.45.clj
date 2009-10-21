; Exercise 1.45

; Procudures below pulled from previous exercises
(defn average [x y]
 (/ (+ x y) 2))

(defn average-damp [f]
 (fn [x] (average x (f x))))

(defn abs [x] (Math/abs x))

(def tolerance 0.00001)
(defn close-enough? [v1 v2] 
 (< (abs (- v1 v2)) tolerance))

(defn repeated [f n]
 (let [helper (fn [g n]
               (if (= n 0) g
                   (recur (fn [x] (f (g x))) (dec n))))]
  (helper f (dec n))))

(defn fixed-point [f first-guess]
 (let [attempt (fn attempt[guess]
          (let [next (f guess)]
                 (if (close-enough? guess next)
                      next
                      (recur next))))]
       (attempt first-guess)))

; New code

(defn pow [a b]
 (Math/pow a b))

(defn nth-root [n x]
 (fixed-point ((repeated average-damp 
                         (Math/ceil (/ n 2.0))) 
                         (fn [y] (/ x  (pow y (dec n)))))
              1.0))


(defn sqrt [x]
 (nth-root 2 x))


(defn cube-root [x]
 (nth-root 3.0 x))

(defn fourth-root [x]
 (nth-root 4.0 x))

(defn fifth-root [x]
 (nth-root 5.0 x))

(defn sixth-root [x]
 (nth-root 6.0 x))

; Use following syntax on wolframalpha.com to get answers
; fifth root of 52
; fourth root of 16
(println (sqrt 4.0))
(println (cube-root 9.0))
(println (fourth-root 16.0))
(println (fifth-root 32.0))
(println (sixth-root 64.0))
