; Exercise 2.67

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
(println (decode sample-message sample-tree))

; vim: set foldmethod=marker:
