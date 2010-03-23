; Exercise 3.64

(defn abs [x] (Math/abs x))

(defn stream-limit [strm tolerance]
  (let [x1 (first strm)
        x2 (second strm)
        diff (abs (- x2 x1))]
    (if (< diff tolerance)
      x2
      (recur (nnext strm) tolerance))))

(defn average [x y]
  (/ (+ x y) 2))

(defn sqrt-improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-stream [x]
  ((fn guesses []
     (lazy-seq
      (cons 1.0
            (map (fn [guess] (sqrt-improve guess x))
                 (guesses)))))))

(defn sqrt [x tolerance]
  (stream-limit (sqrt-stream x) tolerance))
