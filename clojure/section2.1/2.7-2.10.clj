; Exercise 2.7
;
; Implement selectors for Alyssa's interval abstractions

; Constructor
(defn make-interval [a b]
 (list a b))

; Selectors
(defn lower-bound [i]
 (first i))

(defn upper-bound [i]
 (last i))

(defn print-interval [i]
 (println (str "Lower: " (lower-bound i) ", Upper: " (upper-bound i))))

(print-interval (make-interval 1 3))
(print-interval (make-interval 3 4))

; Exercise 2.8
; Implement a sub-interval procedure

; Lower part of interval should be smallest result of 
; subtracing two intervals, hence lower bound of first interval
; minus upper bound of second.
; Upper part of resulting interval should be max of result of
; subtraction, hence upper of first interval minus lower of second.

(defn sub-interval [x y]
 (let [p1 (- (lower-bound x) (upper-bound y))
       p2 (- (upper-bound x) (lower-bound y))]
       (make-interval p1 p2)))

(print-interval (sub-interval (make-interval 1 3)
                              (make-interval 3 4)))

; Exercise 2.9

(defn width [i]
 (/ (- (upper-bound i) (lower-bound i)) 2.0))

; Clojurefied interval functions from book
(defn sum-interval [x y]
 (make-interval (+ (lower-bound x) (lower-bound y))
                (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
 (let [p1 (* (lower-bound x) (lower-bound y))
       p2 (* (lower-bound x) (upper-bound y))
       p3 (* (upper-bound x) (lower-bound y))
       p4 (* (upper-bound x) (upper-bound y))]
  (make-interval (min p1 p2 p3 p4)
                (max p1 p2 p3 p4))))

(defn div-interval [x y]
 (mul-interval x
               (make-interval (/ 1.0 (upper-bound y))
                              (/ 1.0 (lower-bound y)))))
; For addition and subtraction of intervals the width
; of the resulting interval is equal to the sum
; of the input interval widths.

(defn show-sum-and-subtract-interval-widths [i1 i2]
 (println (str "Width of i1: " (width i1)))
 (println (str "Width of i2: " (width i2)))
 (println (str "Sum of widths: " (+ (width i1) (width i2))))
 (println (str "Width of sum of intervals: "
               (width (sum-interval i1 i2))))
 (println (str "Width of difference of intervals: "
           (width (sub-interval i1 i2)))))

(show-sum-and-subtract-interval-widths
 (make-interval 1 5)
 (make-interval 0.2 0.8))

(show-sum-and-subtract-interval-widths
 (make-interval -1 5)
 (make-interval -0.2 0.8))

; The width of the resulting interval from
; multiplying or dividing intervals is not
; a function of the input widths.
; The below doesn't show this conclusively but
; helps show how simple manipulation of widths of
; input intervals do not result in output interval
; width

(defn show-mult-and-div-interval-widths [i1 i2]
 (println (str "Width of i1: " (width i1)))
 (println (str "Width of i2: " (width i2)))
 (println (str "Sum of widths: " (+ (width i1) (width i2))))
 (println (str "Multiplication of widths: " (* (width i1) (width i2))))
 (println (str "Division of widths: " (/ (width i1) (width i2))))
 (println (str "Width of multiplication of intervals: "
               (width (mul-interval i1 i2))))
 (println (str "Width of difference of intervals: "
           (width (div-interval i1 i2)))))

(show-mult-and-div-interval-widths
 (make-interval 1 5)
 (make-interval 0.2 0.8))

(show-mult-and-div-interval-widths
 (make-interval -5 -1)
 (make-interval 0.2 0.8))


; Exercise 2.10
; Ben Bitdiddle does say it isn't clear what it means
; to divide by an interval that spans zero.
; Add a check for this condition
(defn spans-zero? [i]
 (and (>= 0 (lower-bound i))
      (<= 0 (upper-bound i))))

(defn div-interval [x y]
 (if (or (spans-zero? x)
         (spans-zero? y))
      (println "One of the intervals spans zero")
      (mul-interval x
                    (make-interval (/ 1.0 (upper-bound y))
                                   (/ 1.0 (lower-bound y))))))

(div-interval (make-interval -1 1)
              (make-interval 2 4))
(div-interval (make-interval 1 2)
              (make-interval -2 4))
