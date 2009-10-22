; Exercise 2.2

; Define a way of representing line segements in a plane.
; A line segment is represented by 2 points.
; A point is represented by an x and y coordinate.

(defn make-segment [s e]
 (list s e))

(defn start-segment [ls]
 (first ls))

(defn end-segment [ls]
 (last ls))

(defn make-point [x y]
 (list x y))

(defn x-point [p]
 (first p))

(defn y-point [p]
 (last p))

; Could also define y-point and x-point as
; (def y-point last)
; (def x-point first)
; Could do a similar thing with start- and end-segment
; Would not want to do this with the constructors
; as that would allow non-sensical lines and points

(defn avg [a b]
 (/ (+ a b) 2))

(defn midpoint-segment [ls]
 (make-point (avg (x-point (start-segment ls))
                  (x-point (end-segment ls)))
             (avg (y-point (start-segment ls))
                  (y-point (end-segment ls)))))

(defn print-point [p]
 (println (str "(" (x-point p) "," (y-point p) ")")))

; Define two points and a line segement
(def start (make-point 4 5))
(def end (make-point 10 21))
(def line (make-segment start end))

; Print out points and midpoint
(print-point start)
(print-point end)
(print-point (midpoint-segment line))
