(use 'clojure.test)

(defn accumulate [op initial sq] (if (empty? sq)
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

(def empty-board nil)

(defn adjoin-positions [new-row k rest-of-queens] 
 (map (fn [c] (cons (list new-row k) c))
      rest-of-queens))

(defn all? [t]
 (reduce (fn [x y] (and x y))
         true
         t))

(defn safe-line? [op potential]
 (let [f (first potential)
       r (rest potential)]
  (all? (map (fn [x] (not (= (op f) (op x)))) r))))

(defn safe-x? [potential]
 (safe-line? first potential))

(deftest safe-x?-works
 (is (= true (safe-x? '((1 2) (0 0)))))
 (is (= false (safe-x? '((1 2) (1 3))))))


(defn safe-y? [potential]
 (safe-line? second potential))

(deftest safe-y?-works
 (is (= true (safe-y? '((1 2) (0 0)))))
 (is (= false (safe-y? '((1 1) (3 1))))))

(defn safe-diag? [potential]
 (let [f (first potential)
       r (rest potential)]
   (all? (map (fn [x] (not (= 1 
                              (Math/abs (double 
                                         (/
                                           (- (first f) (first x))
                                           (- (second f) (second x))))))))
          r))))

(deftest safe-diag?-works
 (is (= true (safe-diag? '((1 2) (0 0)))))
 (is (= false (safe-diag? '((1 1) (0 0)))))
 (is (= false (safe-diag? '((0 0) (1 1))))))
 

(defn safe? [k positions]
 (and (safe-x? positions) (safe-y? positions) (safe-diag? positions)))


(deftest safe?-works
 (is (= false (safe? 2 '((1 0) (0 0))))) 
 (is (= false (safe? 2 '((1 1) (0 0))))) 
 (is (= true (safe? 2 '((1 2) (0 0))))) )

(defn queens [board-size]
 (let [queen-cols (fn queen-cols [k]
                   (if (= k 0)
                       (list empty-board)
                       (filter
                        (fn [positions] (safe? k positions))
                        (flatmap
                         (fn [rest-of-queens]
                           (map (fn [new-row]
                                  (adjoin-positions new-row k rest-of-queens))
                                (enumerate-interval 1 board-size)))
                           (queen-cols (- k 1))))))]
  (queen-cols board-size)))

(run-tests)

(println (count (queens 4)))
(println (queens 4))
