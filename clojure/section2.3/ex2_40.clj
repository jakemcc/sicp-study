; Exercise 2.40

(use 'clojure.test)

(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(defn enumerate-interval [low high]
 (loop [cur high
        res nil]
  (if (< cur low)
      res
	  (recur (dec cur) (cons cur res)))))

(deftest test-enum-int
 (is (= '(1 2 3 4) (enumerate-interval 1 4)))
 (is (= nil (enumerate-interval 1 0)))
 (is (= '(1) (enumerate-interval 1 1))))

(defn flatmap [proc sq]
 (accumulate concat nil (map proc sq)))

(defn unique-pairs [n]
 (flatmap (fn [i] 
        (map (fn [j] (list i j)) 
	         (enumerate-interval 1 (dec i))))
        (enumerate-interval 1 n)))

(println (unique-pairs 5))

(run-tests)
