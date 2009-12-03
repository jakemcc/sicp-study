; Exercise 2.62
; Give an O(n) implementation of union-set
; representing sets as ordered lists

(use 'clojure.test)

(defn union-set [s1 s2]
 (cond (empty? s1) s2
       (empty? s2) s1
       :else
         (let [x1 (first s1)
               x2 (first s2)]
           (cond (= x1 x2) (cons x1 (union-set (rest s1) (rest s2)))
                 (< x1 x2) (cons x1 (union-set (rest s1) s2))
                 (< x2 x1) (cons x2 (union-set s1 (rest s2)))))))


(deftest should-return-input-on-matching-inputs
 (is (= '(1 2 3) (union-set '(1 2 3) '(1 2 3)))))

(deftest should-handle-one-set-always-smaller
 (is (= '(1 2 3 4 5 6) (union-set '(1 2 3) '(4 5 6)))))

(deftest should-handle-sets-flip-flopping-size
 (is (= '(1 2 3 4 5 6 7) (union-set '(1 3 5 7) '(2 4 6)))))


(run-tests)
