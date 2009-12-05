; Exercise 2.61
(use 'clojure.test)

; Now use ordered lists to represent sets


; Not using any nice things in Clojure
(defn adjoin-set [x s]
 (loop [head '()
        tail s]
  (cond (empty? tail) (concat head (list x))
        (= x (first tail)) (concat head tail)
        (> x (first tail)) (recur (concat head 
                                          (list (first tail)))
                                  (rest tail))
        :else (concat head (list x) tail))))

(deftest adjoin-set-puts-low-element-at-beginning
 (is (= '(1 3 4) (adjoin-set 1 '(3 4)))))

(deftest adjoin-set-puts-element-in-middle
 (is (= '(2 3 4) (adjoin-set 3 '(2 4)))))

(deftest adjoin-set-puts-large-element-at-end
 (is (= '(1 3 4 5) (adjoin-set 5 '(1 3 4)))))

(run-tests)
  
(defn element-of-set? [x s]
 (cond (empty? s) false
       (= x (rest s)) true
       (< x (first s)) false
       :else (element-of-set? x (rest s))))

; Somewhat cheating
(defn adjoin-set [x s]
 (if (element-of-set? x s) s
     (sort (cons x s))))

(run-tests)
