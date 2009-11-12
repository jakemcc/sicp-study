; Exercise 2.41

(use 'clojure.test)

(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(defn flatmap [proc sq]
 (accumulate concat nil (map proc sq)))

(defn enumerate-interval [low high]
 (loop [cur high
        res nil]
  (if (< cur low)
      res
	  (recur (dec cur) (cons cur res)))))

(defn order-triples [n]
 (flatmap (fn [i]
           (flatmap
		     (fn [j]
		       (map (fn [k] (list i j k))
		            (enumerate-interval 1 (dec j))))
             (enumerate-interval 2 (dec i))))
          (enumerate-interval 3 n)))

(defn order-triple-sum-matches? [s]
 (fn [x] (= s (accumulate + 0 x))))

(deftest test-otsm?
 (is (= true ((order-triple-sum-matches? 6) '(1 2 3))))
 (is (= false ((order-triple-sum-matches? 5) '(1 2 3)))))

(defn order-triples-that-sum [n s]
 (let [sum-checker (order-triple-sum-matches? s)]
  (filter sum-checker
          (order-triples n))))

(deftest test-order-triples-sum-works
 (is (= 2 (count (order-triples-that-sum 5 8)))))

(println (order-triples-that-sum 5 8))

(run-tests)
