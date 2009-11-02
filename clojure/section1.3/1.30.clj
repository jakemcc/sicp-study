; Exercise 1.30
;

(defn cube [x] (* x x x))
; Linear recusive version
(defn sum [term a nxt b]
 (if (> a b)
      0
     (+ (term a)
        (sum term (nxt a) nxt b))))

(defn sum-cubes [a b]
 (sum cube a inc b))

(sum-cubes 1 10)

; Iterative version
(defn sum [term a nxt b]
 (let [iter (fn [a result]
              (if (> a b)
                  result
                  (recur (nxt a) (+ result (term a)))))]
  (iter a 0)))

(sum-cubes 1 10)
