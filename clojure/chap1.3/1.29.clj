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

(defn simp-rule [f a b n]
 (let [h (/ (- b a) n)
       next-input (fn [k] (+ a (* k h)))
       sri (fn [k accum]
            (cond (= k n) (+ accum (f (next-input k)))
                  (odd? k) (recur (inc k) (+ accum (* 4 (f (next-input k)))))
                  :else (recur (inc k) (+ accum (* 2 (f (next-input k)))))))]
  (* (/ h 3) (sri 1 (f a)))))

(simp-rule cube 0.0 1.0 100)
(simp-rule cube 0.0 1.0 1000)
