; Exercise 3.56

(ns ex3-56)

(defn merge-streams [s1 s2]
  (cond (nil? s1) s2
	(nil? s2) s1
	:else (let [s1car (first s1)
		    s2car (first s2)]
		(cond (< s1car s2car)
		      (lazy-seq (cons s1car (merge-streams (next s1) s2)))
		      (> s1car s2car)
		      (lazy-seq (cons s2car (merge-streams s1 (next s2))))
		      :else (lazy-seq (cons s1car
					    (merge-streams (next s1)
							   (next s2))))))))

(defn scale-stream [stream factor]
  (map #(* % factor) stream))

(def integers (iterate inc 1))

(def S (lazy-seq (cons 1
		       (merge-streams (scale-stream integers 2)
				      (merge-streams (scale-stream integers 3)
						     (scale-stream integers 5))))))

