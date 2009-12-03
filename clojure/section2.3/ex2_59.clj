; Exercise 2.59

(use 'clojure.test)

(defn element-of-set? [x s]
 (cond (empty? s) false
       (= x (first s)) true
       :else (element-of-set? x (rest s))))

(deftest element-of-set?-works
 (is (element-of-set? 1 '(1 2 3)))
 (is (not (element-of-set? 4 '(1 2 3)))))

(defn union-set [set1 set2]
 (cond (or (empty? set1) (empty? set2)) set2 
       (element-of-set? (first set1) set2)
         (union-set (rest set1) set2)
       :else (cons (first set1)
                   (union-set (rest set1) set2))))


(deftest should-return-simple-union
 (is (= '(3 4 5 1) (union-set '(3 4 5) '(1)))))

(deftest should-return-same-set
 (is (= '(1 2 3) (union-set '(1 2 3) '(1 2 3)))))

(run-tests)
