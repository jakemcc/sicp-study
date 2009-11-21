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



(defn queens [board-size]
 (let [queen-cols (fn [k]
                   (if (= k 0)
                       (list empty-board)
                       (filter
                        (fn [positions] (safe? k positions))
                        (flatmap
                         (fn [rest-of-queens]
                           (map (fn [new-row]
                                  (adjoin-poisition new-row k rest-of-queens))
                                (enumerate-interval 1 board-size)))
                           (recur (- k 1))))))]
  (queen-cols board-size)))

(defn adjoin-positions [new-row k rest-of-queens] )

(def empty-board nil)

(defn safe? [k positions] )
