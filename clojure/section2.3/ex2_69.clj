; Exercise 2.69
;
; Write successive-merge to finish off the below
; procedure which generates a huffmann tree

(use 'clojure.test)

;{{{ Functions from book
(defn make-leaf [sym weight]
 (list 'leaf sym weight))

(defn leaf? [obj] (= (first obj) 'leaf))
(defn symbol-leaf [x] (second x))
(defn weight-leaf [x] (nth x 2))

(defn symbols [tree]
 (if (leaf? tree)
     (list (symbol-leaf tree))
     (nth tree 2)))

(defn weight [tree]
 (if (leaf? tree)
     (weight-leaf tree)
     (nth tree 3)))

(defn make-code-tree [left right]
 (list left
       right
       (concat (symbols left) (symbols right))
       (+ (weight left) (weight right))))

(defn weight [tree]
 (if (leaf? tree)
     (weight-leaf tree)
     (nth tree 3)))

(defn adjoin-set [x s]
 (cond (empty? s) (list x)
       (< (weight x) (weight (first s))) (cons x s)
       :else (cons (first s)
                   (adjoin-set x (rest s)))))

(defn make-leaf-set [pairs]
 (if (empty? pairs) '()
     (let [pair (first pairs)]
      (adjoin-set (make-leaf (first pair) (second pair))
                  (make-leaf-set (rest pairs))))))
;}}}

(defn successive-merge [pairs]
 (if (= 1 (count pairs)) (first pairs)
     (let [lowest-weight (first pairs)
           second-lowest-weight (second pairs)
           combined-first-two (make-code-tree lowest-weight second-lowest-weight)
           remaining-pairs (rest (rest pairs))
           combined-pairs (adjoin-set combined-first-two remaining-pairs)]
     (successive-merge combined-pairs))))

(defn generate-huffman-tree [pairs]
 (let [p (make-leaf-set pairs)]
 (successive-merge p)))


(def sample-tree
 (make-code-tree (make-leaf 'A 4)
                 (make-code-tree
                  (make-leaf 'B 2)
                  (make-code-tree (make-leaf 'D 1)
                                  (make-leaf 'C 1)))))

(deftest generated-tree-matches-sample
 (is (= sample-tree (generate-huffman-tree '((A 4) (B 2) (C 1) (D 1))))))

(run-tests)

; vim: set foldmethod=marker:
