; Exercise 2.29
(ns ex2_29)

(defstruct mobile :left :right)
(defn make-mobile [left right]
 (struct mobile left right))

(defstruct branch :length :structure)
(defn make-branch [length structure]
 (struct branch length structure))


; Write corresponding selectors left-branch and right-branch,
; which return the branches of the mobile, and branch-length
; and branch-structure, which return the components of a branch

(defn branch-structure [branch]
 (:structure branch))
(defn branch-length [branch]
 (:length branch))

(defn left-branch [mobile]
 (:left mobile))
(defn right-branch [mobile]
 (:right mobile))

; Using my selectors, define total-weight which returns total weight
; of a mobile

(defn branch? [x] 
 (and (contains? x :structure)
      (contains? x :length)))

(defn mobile? [x] 
 (and (contains? x :left)
      (contains? x :right)))

(defn total-weight [x]
 (cond (mobile? x)
         (+ (total-weight (left-branch x))
            (total-weight (right-branch x)))
       :else
         (let [structure (branch-structure x)]
          (if (mobile? structure) (total-weight structure)
              structure))))

(def simple (make-mobile 
             (make-branch 4 10)
             (make-branch 5 9)))

(def complicated (make-mobile 
                   (make-branch 4 (make-mobile (make-branch 1 4)
                                               (make-branch 4 16)))
                   (make-branch 5 (make-mobile (make-branch 1 2)
                                               (make-branch 4 7)))))

; should be 19
(println (total-weight simple))
; Should be 29
(println (total-weight complicated))


