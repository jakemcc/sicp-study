; Exercise 2.65
;
; Question:
;   Give O(n) implementations of union-set and intersection-set
;   for sets implemented as (balanced) binary trees

(use 'clojure.test)

;{{{ Library functions
(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (nth tree 2))
(defn make-tree [entry left right]
  (list entry left right))

(defn quotient [x y]
 (int (/ x y)))

(defn partial-tree [elts n]
  (if (= n 0)
      (cons '() elts)
      (let [left-size (quotient (- n 1) 2)]
        (let [left-result (partial-tree elts left-size)]
          (let [left-tree (first left-result)
                non-left-elts (rest left-result)
                right-size (- n (+ left-size 1))]
            (let [this-entry (first non-left-elts)
                  right-result (partial-tree (rest non-left-elts)
                                              right-size)]
              (let [right-tree (first right-result)
                    remaining-elts (rest right-result)]
                (cons (make-tree this-entry left-tree right-tree)
                      remaining-elts))))))))

(defn list->tree [elements]
  (first (partial-tree elements (count elements))))

(defn tree->list-2 [tree]
  (letfn [(copy-to-list [tree result-list]
           (if (empty? tree)
                 result-list
               (copy-to-list (left-branch tree)
                             (cons (entry tree)
                                   (copy-to-list (right-branch tree)
                                                  result-list)))))]
  (copy-to-list tree '())))

(defn union-set-lists [s1 s2]
 (cond (empty? s1) s2
       (empty? s2) s1
       :else
         (let [x1 (first s1)
               x2 (first s2)]
           (cond (= x1 x2) (cons x1 (union-set-lists (rest s1) (rest s2)))
                 (< x1 x2) (cons x1 (union-set-lists (rest s1) s2))
                 (< x2 x1) (cons x2 (union-set-lists s1 (rest s2)))))))

(defn intersection-set-lists [set1 set2]
  (if (or (empty? set1) (empty? set2))
      '()    
      (let [x1 (first set1)
            x2 (first set2)]
        (cond (= x1 x2)
               (cons x1
                     (intersection-set-lists (rest set1)
                                       (rest set2)))
              (< x1 x2)
               (intersection-set-lists (rest set1) set2)
              (< x2 x1)
               (intersection-set-lists set1 (rest set2))))))

(defn adjoin-set [x set]
  (cond (empty? set) (make-tree x '() '())
        (= x (entry set)) set
        (< x (entry set))
         (make-tree (entry set) 
                    (adjoin-set x (left-branch set))
                    (right-branch set))
        (> x (entry set))
         (make-tree (entry set)
                    (left-branch set)
                    (adjoin-set x (right-branch set)))))
;}}}

; Basic idea
; Convert trees to ordered lists in O(n)
; Perform O(n) intersection or union
; Convert ordered list to tree in O(n)

(defn union-set [set1 set2]
 (let [list1 (tree->list-2 set1)
       list2 (tree->list-2 set2)]
   (->> (union-set-lists list1 list2)
        (list->tree))))

(defn intersection-set [set1 set2]
 (let [list1 (tree->list-2 set1)
       list2 (tree->list-2 set2)]
   (->> (intersection-set-lists list1 list2)
        (list->tree))))

(deftest union-set-works
 (is (= (list->tree '(1 2 3 4 5))
        (union-set (list->tree '(1 3 5))
                   (list->tree '(2 4))))))

(deftest intersection-set-works
 (is (= (list->tree '(3))
        (intersection-set (list->tree '(1 3 5))
                   (list->tree '(2 3 4))))))


(run-tests)
; vim: set foldmethod=marker:
