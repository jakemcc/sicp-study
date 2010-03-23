; Exercise 3.65

(defn square [x] (* x x))

(defn euler-transform [s]
  (let [s0 (nth s 0)
        s1 (nth s 1)
        s2 (nth s 2)]
    (lazy-seq
     (cons (- s2 (/ (square (- s2 s1))
                    (+ s0 (* -2 s1) s2)))
           (euler-transform (next s))))))

(defn make-tableau [transform s]
  (lazy-seq
   (cons s
         (make-tableau transform
                       (transform s)))))

(defn accelerated-sequence [transform s]
  (map first (make-tableau transform s)))

(def ln-2-summands
     (map double
          (map /
               (cycle '(1 -1))
               (iterate inc 1))))

(defn partial-sums [s]
  (lazy-seq
   (cons (first s) (map + (next s) (partial-sums s)))))

(def ln-2-stream
     (partial-sums ln-2-summands))

(def euler-ln-2-stream
     (euler-transform ln-2-stream))

(def accelerated-ln-2-stream
     (accelerated-sequence euler-transform ln-2-stream))

;Going to modify stream-limit to return what element it reaches tolerance on
(defn stream-limit [strm tolerance n]
  (let [x1 (first strm)
        x2 (second strm)
        diff (abs (- x2 x1))]
    (if (< diff tolerance)
      {:elem n :value x2}
      (recur (nnext strm) tolerance (inc n)))))

; Google says: ln(2) = 0.693147181
;user> (stream-limit euler-ln-2-stream 0.000000001 0)
;{:elem 314, :value 0.6931471800635955}
;user> (stream-limit accelerated-ln-2-stream 0.000000001 0)
;{:elem 3, :value 0.6931471805599443}
;user> (stream-limit ln-2-stream 0.000000001 0)
; Evaluation aborted. (had a stack overflow error)

