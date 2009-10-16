; Exercise 1.29

; Code from book to compare answer with
(defn sum [term a nxt b]
 (if (> a b) 0
     (+ (term a)
        (sum term (nxt a) nxt b))))

(defn integral [f a b dx]
 (let [add-dx (fn [x] (+ x dx))]
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
     dx)))

(defn cube [x] (* x x x))

(integral cube 0 1 0.01)
(integral cube 0 1 0.001)



(defn h [a b n]
 (/ (- b a) n))

; (defn simpsons-rule [f a b n]
;  (let [h (/ (- b a) n)
;        helper (fn [k accum]
;            (let [x ((+ a (* k h)))]
;             (cond (= k 0) (helper (inc k) (+ accum (f x)))
;                   (= k n) (+ accum (f x))
;                   (even? k) (+ (* 2 (f x)) (helper (inc k) accum))
;                   :else (+ (* 4 (f x)) (helper (inc k) accum)))))]
;        (helper 0 0)))
