; Exercise 2.66
;
; Implement a lookup procedure which works on binary trees
;

(defn make-record [k v]
 (list k v))
(defn get-key [record]
 (first record))
(defn get-value [record]
 (second record))

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (nth tree 2))
(defn make-tree [entry left right]
  (list entry left right))

(defn lookup [given-key tree-of-records]
 (if (empty? tree-of-records) false
     (let [actual-key (get-key (entry tree-of-records))]
       (cond (= given-key actual-key) true
             (< given-key actual-key)
               (lookup given-key (left-branch tree-of-records))
             (> given-key actual-key)
               (lookup given-key (right-branch tree-of-records))))))

; lookup is the same as element-of-set? for
; binary trees
