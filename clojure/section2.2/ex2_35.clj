; Exercise 2.35
(use 'clojure.test)

(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(defn count-leaves [t]
 (accumulate + 0 (map (fn [x]
                       (if (seq? x) 
                             (count-leaves x)
                            1))
                        t)))

(deftest test-count-leaves
 (is (= 3 (count-leaves '(1 (2 3)))))
 (is (= 5 (count-leaves '((8 7 9) (2 3))))))

(run-tests)
