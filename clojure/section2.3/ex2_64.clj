; Exercise 2.64
;

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
     (let [left-size      (quotient (- n 1) 2)
           left-result    (partial-tree elts left-size)
           left-tree      (first left-result)
           non-left-elts  (rest left-result)
           right-size     (- n (+ left-size 1))
           this-entry     (first non-left-elts)
           right-result   (partial-tree (rest non-left-elts) right-size)
           right-tree     (first right-result)
           remaining-elts (rest right-result)]
       (cons (make-tree this-entry left-tree right-tree)
             remaining-elts))))

(defn list->tree [elements]
  (first (partial-tree elements (count elements))))


; A)
;    Question:
;      Write a short paragraph explaining as clearly
;      as you can how partial-tree works. Draw the 
;      tree produced by list->tree for the list (1 3 5 7 9 11). 
;
;    Answer:
;      partial-tree divides the input list into two sub lists, a left
;      and right list.  Then it calls itself and keeps dividing lists
;      into left and right elements. It uses these sublists to build
;      sub trees which are all combined to create overall tree
;          5 
;        /   \
;       1      9
;        \    /  \
;         3   7  11
; B) Question:
;      What is the order of growth in the number of steps
;      required by list->tree to convert a list of n elements?
;
;    Answer:
;      O(n)

(println (list->tree '(1 3 5 7 9 11)))
