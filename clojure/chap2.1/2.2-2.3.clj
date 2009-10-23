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



; Exercise 2.3
;
; Design an 2 abstractions for rectangles.  Implement a single
; area and perimeter function which takes a rectangle of either
; abstraction and figures out the area or perimeter

; Rectangle defined by four points
(defn make-rectangle [p1 p2 p3 p4]
 (list p1 p2 p3 p4))

(defn top-left-corner [r]
 (first r))

(defn top-right-corner [r]
 (nth r 1))

(defn bottom-left-corner [r]
 (nth r 2))

(defn bottom-right-corner [r]
 (last r))

(defn x-diff [p1 p2]
 (Math/abs (- (x-point p1) (x-point p2))))

(defn y-diff [p1 p2]
 (Math/abs (- (y-point p1) (y-point p2))))

(defn area [r]
 (* (x-diff (top-left-corner r) (top-right-corner r))
    (y-diff (top-right-corner r) (bottom-right-corner r))))

(defn perimeter [r]
 (* 2 (+ (x-diff (top-left-corner r) (top-right-corner r))
         (y-diff (top-left-corner r) (bottom-left-corner r)))))

(area (make-rectangle
       (make-point 1 1)
       (make-point 5 1)
       (make-point 1 4)
       (make-point 5 4)))

(perimeter (make-rectangle
            (make-point 1 1)
            (make-point 5 1)
            (make-point 1 4)
            (make-point 5 4)))

; Rectangle defined by 2 points
(defn make-rectangle [p1 p2]
 (list p1 p2))

(defn top-left-corner [r]
 (first r))

(defn top-right-corner [r]
 (make-point (x-point (top-left-corner r))
             (y-point (bottom-right-corner r))))

(defn bottom-left-corner [r]
 (make-point (y-point (top-left-corner r))
             (x-point (bottom-right-corner r))))

(defn bottom-right-corner [r]
 (last r))

(def x (make-rectangle
       (make-point 1 1)
       (make-point 5 4)))
(top-left-corner x)
(top-right-corner x)
(bottom-left-corner x)
(bottom-right-corner x)
(area x)

(perimeter (make-rectangle
            (make-point 1 1)
            (make-point 5 4)))
