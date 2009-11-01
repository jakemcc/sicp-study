; Exercise 2.2

; Define a way of representing line segements in a plane.
; A line segment is represented by 2 points.
; A point is represented by an x and y coordinate.

; Define segments and points using lists and positions to tell what
; data represents
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
    ;
; Could also define y-point and x-point as
; (def y-point last)
; (def x-point first)
; Could do a similar thing with start- and end-segment
; Would not want to do this with the constructors
; as that would allow non-sensical lines and points

; Could also use Clojures defstruct
(defstruct segment :start :end)
(defn make-segment [s e]
 (struct segment s e))

(defn start-segment [ls]
 (:start ls))

(defn end-segment [ls]
 (:end ls))

(defstruct point :x :y)
(defn make-point [x y]
 (struct point x y))

(defn x-point [p]
 (:x p))

(defn y-point [p]
 (:y p))


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



; Exercise 2.3
;
; Design an 2 abstractions for rectangles.  Implement a single
; area and perimeter function which takes a rectangle of either
; abstraction and figures out the area or perimeter

; Rectangle defined by length and width points
(defn make-rectangle [length width]
 (list length width))

(defn length [r]
 (first r))

(defn width [r]
 (last r))

(defn area [r]
 (* (length r)
    (width r)))

(defn perimeter [r]
 (* 2 (+ (length r)
         (width r))))

(println (area (make-rectangle 7 5)))
(println (perimeter (make-rectangle 7 5)))

; Rectangle defined by 2 points
(defn make-rectangle [p1 p2]
 (list p1 p2))

(defn length [r]
 (Math/abs (- (y-point (first r))
              (y-point (last r)))))

(defn width [r]
 (Math/abs (- (x-point (first r))
              (x-point (last r)))))


(def x (make-rectangle
       (make-point 1 1)
       (make-point 8 6)))

(println (area x))

(println (perimeter x))
