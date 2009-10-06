; exercise 1.8

(defn change [first second]
    (Math/abs (- first second)))

(defn good-enough? [first second]
    (< (change first second) (* 0.001 second)))

(defn square [x] (* x x))

(defn improve [guess x]
    (/ (+ (/ x (square guess)) (* 2.0 guess))
        3))

(defn cube-root-iter [prev-guess curr-guess x]
    (if (good-enough? prev-guess curr-guess)
        curr-guess
        (cube-root-iter curr-guess (improve curr-guess x) x)))

(defn cube-root [x]
    (cube-root-iter 0 1.0 x))

(println (cube-root 9))


; user=> (cube-root 9)
; 2.0800838232385224
