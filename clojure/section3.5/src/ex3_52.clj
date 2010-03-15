; Exercise 3.52

; **** WARNING ****
; Solutions here are going to differ some from solutions to problem in the book.
; Clojure's lazy streams and the stream implementation done in the book have different
; ways of evaluating. This will result in differences.

; One of those major differences in Clojure's 'chunked' stream concept. Streams are
; evalulated in chunks in Clojure instead of always one at a time.

; Because I'm choosing to do these with native Clojure constructs intead of
; reimplementing the Scheme style described in SICP the solutions are going to
; be different. The Scheme style could be easily implemented using Clojure's 'delay and 'force
; procedures if one was so inclined.

(defn display-stream [s]
  (doseq [x s]
    (println x)))

(def sum (atom 0))
; sum is 0

(defn accum [x]
  (swap! sum #(+ x %))
  x)
; sum is still 0

(def seqs (map accum (range 1 20)))
; sum is 0

(def y (filter even? seqs))
; sum is 0

(def x (filter (fn [x] (zero? (rem x 5)))
	       seqs))
; sum is 0

(nth y 7)
; sum is 190, it is this because of chuncked evaluation
; Result of expression is 16.

(display-stream x)
; prints to repl:
; 5
; 10
; 15
; sum is still 190

; The above assumes that delayed evaluation results are cached and only
; evaluated once.  If this were not true then accum would get called with (range 1 7) from
; the (nth y 7) call and then again with (range 1 20) for the diplay-stream call. This
; would result in sum being equal to 218.