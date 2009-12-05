; Exercise 2.63


(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (nth tree 2))
(defn make-tree [entry left right]
  (list entry left right))

(defn tree->list-1 [tree]
  (if (empty? tree)
      '()
      (concat (tree->list-1 (left-branch tree))
              (cons (entry tree)
                    (tree->list-1 (right-branch tree))))))

(defn tree->list-2 [tree]
  (letfn [(copy-to-list [tree result-list]
           (if (empty? tree)
                 result-list
               (copy-to-list (left-branch tree)
                             (cons (entry tree)
                                   (copy-to-list (right-branch tree)
                                                  result-list)))))]
  (copy-to-list tree '())))
;
;A) Question:
;     Do the two procedures produce the same result for every tree?
;     If not, how do the results differ?
;     What lists do the two procedures produce for the trees in figure 2.16? 
;
;   Answer:
;     The same result is returned for every tree.  An in order list
;     is the result.
;     The result is '(1 3 5 7 9 11)
;
;
;B) Question:
;     Do the two procedures have the same order of growth 
;     in the number of steps required to convert a balanced 
;     tree with n elements to a list?
;     If not, which one grows more slowly? 
;
;   Answer:
;     There is no difference. Both O(n)

(def fig216a (make-tree 7 
              (make-tree 3 
                 (make-tree 1 '() '())
                 (make-tree 5 '() '()))
              (make-tree 9 
                 '() 
                 (make-tree 11 '() '()))))

(def fig216b (make-tree 3
               (make-tree 1 '() '())
               (make-tree 7
                 (make-tree 5 '() '())
                 (make-tree 9 '() (make-tree 11 '() '())))))

(def fig216c (make-tree 5
               (make-tree 3
                 (make-tree 1 '() '())
                 '())
               (make-tree 9
                 (make-tree 7 '() '())
                 (make-tree 11 '() '()))))
(println (tree->list-1 fig216a))
(println (tree->list-2 fig216a))
(println (tree->list-1 fig216b))
(println (tree->list-2 fig216b))
(println (tree->list-1 fig216c))
(println (tree->list-2 fig216c))
