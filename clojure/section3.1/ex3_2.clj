; Exercise 3.2

(use 'clojure.test)

(defn make-monitored [f]
 (let [num-calls (atom 0)]
  (fn [x]
   (if (= x 'how-many-calls?)
       @num-calls
       (do 
        (swap! num-calls inc)
        (f x))))))

(deftest test-make-monitored
 (let [m-inc (make-monitored inc)]
  (is (= 1 (m-inc 0)))
  (is (= 5 (m-inc 4)))
  (is (= 2 (m-inc 'how-many-calls?)))))

(run-tests)
