; Exericse 3.30

(ns ex3_30)

(defn full-adder [a b c-in sum c-out]
  (let [s (make-wire)
	c1 (make-wire)
	c2 (make-wire)]
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)
    :ok))

(defn make-wires [n]
  (for [x (range n)]
    (make-wire)))

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
