; Exercise 2.31

(use 'clojure.test)

(defn tree-map [op tree]
 (map (fn [sub-tree]
       (if (seq? sub-tree)
           (tree-map op sub-tree)
           (op sub-tree)))
  tree))

(defn square-tree [tree]
 (tree-map (fn [x] (* x x)) tree))


(deftest square-tree-example-works
    (is (= '(1 (4 (9 16) 25) (36 49))
            (square-tree '(1 (2 (3 4) 5) (6 7))))))

(run-tests)
