; Exercise 2.67 and 2.68

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
; Finish an encode function.  Test against solution from 2.67
    
(defn encode-symbol [elem tree]
 (letfn [(contains-e? [syms] (some #(= % elem) syms))]
  (cond (and (leaf? tree)) '()
        (contains-e? (symbols (right-branch tree))) 
          (cons 1 (encode-symbol elem (right-branch tree)))
        (contains-e? (symbols (left-branch tree))) 
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


(run-tests)
; vim: set foldmethod=marker:
