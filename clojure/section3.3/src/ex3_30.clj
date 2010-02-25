; Exericse 3.30

(ns ex3_30
  (:use circuit-building-blocks))

(defn sandwich-list
  [start middle end]
  (reverse
   (list* end
	  (reverse (list* start middle)))))

(defn ripple-carry-adder
  [As Bs Ss C]
  (let [n (count As)
	Cs (sandwich-list C (make-wires (- n 1)) 0)]
    (loop [as As, bs Bs, cs Cs, ss Ss]
      (if (nil? as) :ok)
      (full-adder (first as)
		  (first bs)
		  (second cs)
		  (first ss)
		  (first cs))
      (recur (next as)
	     (next bs)
	     (next cs)
	     (next ss)))))
