; Huffman Encoding problems

(use 'clojure.test)

;{{{ Needed supporting functions
(defn make-leaf [sym weight]
 (list 'leaf sym weight))

(defn leaf? [obj] (= (first obj) 'leaf))
(defn symbol-leaf [x] (second x))
(defn weight-leaf [x] (nth x 2))

(defn weight [tree]
 (if (leaf? tree)
     (weight-leaf tree)
     (nth tree 3)))

(defn symbols [tree]
 (if (leaf? tree)
     (list (symbol-leaf tree))
     (nth tree 2)))

(defn make-code-tree [left right]
 (list left
       right
       (concat (symbols left) (symbols right))
       (+ (weight left) (weight right))))

(defn left-branch [tree] (first tree))
(defn right-branch [tree] (second tree))


(defn choose-branch [bit branch]
 (cond (= bit 0) (left-branch branch)
       (= bit 1) (right-branch branch)))

(defn decode [bits tree]
 (letfn [(decode-1 [bits current-branch]
          (if (empty? bits) '()
              (let [next-branch (choose-branch (first bits) current-branch)]
               (if (leaf? next-branch)
                   (cons (symbol-leaf next-branch)
                         (decode-1 (rest bits) tree))
                   (decode-1 (rest bits) next-branch)))))]
  (decode-1 bits tree)))

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


; Problem 2.67

(def sample-tree
 (make-code-tree (make-leaf 'A 4)
                 (make-code-tree
                  (make-leaf 'B 2)
                  (make-code-tree (make-leaf 'D 1)
                                  (make-leaf 'C 1)))))

(def sample-message '(0 1 1 0 0 1 0 1 0 1 1 1 0))

; Question: What is the message in sample-message
;
; Answer:
(deftest decode-works-on-samples
 (is (= '(A D A B B C A) (decode sample-message sample-tree))))


; Exercise 2.68
; Finish an encode-symbol function.  Test against solution from 2.67
    
(defn encode-symbol [elem tree]
 (letfn [(contains-elem? [syms] (some #(= % elem) syms))]
  (cond (and (leaf? tree)) '()
        (contains-elem? (symbols (right-branch tree))) 
          (cons 1 (encode-symbol elem (right-branch tree)))
        (contains-elem? (symbols (left-branch tree))) 
          (cons 0 (encode-symbol elem (left-branch tree)))
        :else 'error)))


(deftest encode-symbol-finds-element
 (is (= '(0) (encode-symbol 'A sample-tree)))
 (is (= '(1 1 0) (encode-symbol 'D sample-tree)))
 (is (= '(1 1 1) (encode-symbol 'C sample-tree)))
 (is (= '(1 0) (encode-symbol 'B sample-tree))))

(deftest encode-puts-error-if-no-encoding-found
 (is (= 'error (encode-symbol 'X sample-tree))))

(defn encode [message tree]
 (if (empty? message)
     '()
     (concat (encode-symbol (first message) tree)
             (encode (rest message) tree))))

(deftest encode-works-on-samples
 (is (= sample-message (encode (decode sample-message sample-tree) sample-tree))))


; Exercise 2.69

(defn successive-merge [pairs]
 (if (= 2 (count pairs)) (make-code-tree (first pairs) (second pairs))
     (let [lowest-weight (first pairs)
           second-lowest-weight (second pairs)
           combined-first-two (make-code-tree lowest-weight second-lowest-weight)
           remaining-pairs (rest (rest pairs))
           combined-pairs (adjoin-set combined-first-two remaining-pairs)]
       (successive-merge combined-pairs))))

(defn generate-huffman-tree [pairs]
 (successive-merge (make-leaf-set pairs)))

(deftest generated-tree-matches-sample
 (is (= sample-tree (generate-huffman-tree '((A 4) (B 2) (C 1) (D 1))))))

; Can't do this, might be a limitation of functions from book
; Had check in successive-merge for if pairs < 2, but then make-code-tree could not
; handle it
;(deftest generated-tree-matches-sample
; (is (= sample-tree (generate-huffman-tree '((A 4))))))


; Exercise 2.71

(def tree (generate-huffman-tree '((A 2) (NA 16) (BOOM 1) (SHA 3)
                                   (GET 2) (YIP 9) (JOB 2) (WAH 1))))

(def song '(GET A JOB
            SHA NA NA NA NA NA NA NA NA GET A JOB
            SHA NA NA NA NA NA NA NA NA
            WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP
            SHA BOOM))

(def encoded-message (encode song tree))

(println "Number of bits needed to encode song:" (count encoded-message))

;log2(8) = 3
(println "Number of bits if using fixed length:" (* (count song) 3))




; Exercise 2.71

; Not going to draw this out.  Here is for n = 5
(println (generate-huffman-tree '((a 1) (b 2) (c 4) (d 8) (e 16))))

; There will always be one bit required for the most frequent symbol.
;
; For the least frequent symbol it will be (n - 1) bits required.  With
; each symbol having a frequency of a power of 2 it results in a linear
; path down the tree to get to the least frequent element.


; Exercise 2.72
; O(n^2). Worst case are trees described in exercise 2.71. To get to 
; least used word need to traverse entire tree.  At each level need
; to search the list of elements to see if we are going
; the correct direction. Those sets were implemented as unordered lists
; so search time on them is O(n).  You do this search at each of the n
; levels.  So O(n*n) = O(n^2)



(run-tests)




; vim: set foldmethod=marker:
