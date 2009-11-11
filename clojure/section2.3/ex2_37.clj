; Exercise 2.37
(use 'clojure.test)

(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(defn accumulate-n [op init seqs]
 (if (empty? (first seqs))
	 nil
	 (cons (accumulate op init (map first seqs))
	       (accumulate-n op init (map rest seqs)))))



(def matrix '((1 2 3 4) (4 5 6 6) (6 7 8 9)))
; Matrix represented by matrix
; [1 2 3 4]
; [4 5 6 6]
; [6 7 8 9]

(defn dot-product [v w]
 (accumulate + 0 (map * v w)))

(println (dot-product '(2 4 6) '(3 5 7)))

; I think there may be a different way of doing this.
; book only had ?? before the second parameter to map,
; I ended up making map take another list of mulitpliers
(defn matrix-*-vector [m v]
 (map (fn [s a] (* a (accumulate + 0 s)))
	   m
	   v))

(deftest test-matrix-*-vector
 (is (= '(10 42 0)
	     (matrix-*-vector matrix '(1 2 0)))))

(defn transpose [m]
 (accumulate-n cons nil m))
; [1 2 3 4]
; [4 5 6 6]
; [6 7 8 9]
; TRANPOSED
; [1 4 6]
; [2 5 7]
; [3 6 8]
; [4 6 9]
(deftest test-transpose
 (is (= '((1 4 6) (2 5 7) (3 6 8) (4 6 9)) (transpose matrix))))

(defn matrix-*-matrix [mat1 mat2]
 (let [cols (transpose mat2)]
  (map (fn [r]
		(map 
		 (fn [c] 
		  (accumulate 
	                +
					0
					(map * r c)))
		 cols))
		mat1)))

; [1 2 3 4]   [1 4 6]   [30  56  80]
; [4 5 6 6] * [2 5 7] = [56 113 161]
; [6 7 8 9]   [3 6 8]   [80 161 230]
;             [4 6 9]    

(def expected '((30 56 80) (56 113 161) (80 161 230)))
(deftest test-matrix-*-matrix
 (is (= expected
	    (matrix-*-matrix matrix (transpose matrix)))))


(run-tests)
