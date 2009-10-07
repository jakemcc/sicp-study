; exercise 1.8

(defn diff-between [first second]
    (Math/abs (- first second)))

(defn good-enough? [first second]
    (< (diff-between first second) (* 0.001 second)))

(defn square [x] (* x x))

(defn improve [guess x]
    (/ (+ (/ x (square guess)) (* 2.0 guess))
        3))

(defn cube-root [x]
 (let [cube-root-iter (fn [prev-guess curr-guess]
                        (if (good-enough? prev-guess curr-guess)
                            curr-guess
                            (recur curr-guess (improve curr-guess x) )))]
    (cube-root-iter 0 1.0)))

(println (cube-root 9))


; user=> (cube-root 9)
; 2.0800838232385224
