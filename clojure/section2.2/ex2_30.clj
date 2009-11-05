; Exercise 2.30

(use 'clojure.test)

(defn square [x] (* x x))
(defn square-tree [tree]
 (cond (not (seq? tree)) (square tree)
       (empty? tree) nil
       :else (cons (square-tree (first tree))
                   (square-tree (rest tree)))))

(deftest square-tree-example-works
    (is (= '(1 (4 (9 16) 25) (36 49))
            (square-tree '(1 (2 (3 4) 5) (6 7))))))

(run-tests)

(defn square-tree [tree]
 (map (fn [sub-tree]
       (if (seq? sub-tree)
           (square-tree sub-tree)
           (square sub-tree)))
  tree))

(run-tests)
