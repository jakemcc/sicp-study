; Exercise 2.33

(use 'clojure.test)

; clojurefied accumulate from book
(defn accumulate [op initial sq]
 (if (empty? sq)
     initial
     (op (first sq)
         (accumulate op initial (rest sq)))))

(deftest test-accumulate
 (is (= 15 (accumulate + 0 (list 1 2 3 4 5)))))



(defn my-map [p sq]
 (accumulate (fn [x y] (cons (p x) y)) nil sq))

(deftest test-my-map
 (is (= '(2 3 4 5 6) (my-map inc (list 1 2 3 4 5)))))



(defn append [seq1 seq2]
 (accumulate cons seq2 seq1))

(deftest test-append
 (is (= '(1 2 3 4 5 6) (append '(1 2 3) '(4 5 6)))))


(defn length [sq]
 (accumulate (fn [x y] (inc y)) 0 sq))

(deftest test-length
 (is (= 5 (length '(1 2 3 4 5)))))



(run-tests)
