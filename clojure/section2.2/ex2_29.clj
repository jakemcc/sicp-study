; Exercise 2.29
(ns ex2_29
 (:use clojure.test))

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
(deftest total-weight-works
 (is (= 19 (total-weight simple)))
 (is (= 29 (total-weight complicated)))
 (is (= 12 (total-weight (make-branch 3 (make-mobile (make-branch 2 4)
                                                     (make-branch 1 8)))))))


; Part B
; A mobile is balanced if the torque applied by top-left branch is 
; equal to that applied by top-right branch (that is, if the length of left
; rod multiplied by weight hanging from that rod is equal to same product on right side) and
; if each submobile hanging off its branches is balanced.  Design a predicate that tests whether
; a binary mobile is balanced.

(defn torque [x]
 (* (branch-length x)
    (total-weight x)))



(deftest torque-works
    (is (= 44 (torque (make-branch 4 (make-mobile (make-branch 1 5) (make-branch 2 6))))))
    (is (= 36 (torque (make-branch 3 (make-mobile (make-branch 2 4)
                                                  (make-branch 1 8))))))
    (is (= 36 (torque (make-branch 6 (make-mobile (make-branch 2 3)
                                                  (make-branch 2 3)))))))



(defn balanced? [m]
 (if (mobile? m)
          (and (balanced? (left-branch m))
               (balanced? (right-branch m))
               (= (torque (left-branch m)) (torque (right-branch m))))
      (if (mobile? (branch-structure m))
             (balanced? (branch-structure m))
           true)))



(deftest works-with-simple-mobile-is-balanced
 (is (balanced? (make-mobile (make-branch 2 3)
                             (make-branch 3 2))))
 (is (not (balanced? (make-mobile (make-branch 2 5)
                                  (make-branch 3 2))))))

(deftest works-with-mobiles-with-balanced-submobiles
 (is (balanced? (make-mobile (make-branch 6 (make-mobile (make-branch 2 3)
                                                         (make-branch 2 3)))
                             (make-branch 3 (make-mobile (make-branch 2 4)
                                                         (make-branch 1 8)))))))
 
(deftest non-balanced-submobiles-cause-non-balanced-root-module
 ; Show one branch is not balanced and other is
 (is (not (balanced? (make-mobile (make-branch 2 5)
                                  (make-branch 1 9)))))
 (is (balanced? (make-mobile (make-branch 2 1)
                             (make-branch 2 1))))
 (is (not (balanced? (make-mobile (make-branch 21 (make-mobile (make-branch 2 1)
                                                               (make-branch 2 1)))
                                  (make-branch 3 (make-mobile (make-branch 2 5)
                                                              (make-branch 1 9))))))))

(run-tests)

; d) If we change the representation of mobiles and branches, what
; needs to change to have the rest of the program work?
;
; Answer:
;   The selectors would need to change.  So would the predicates I created
;   to tell if something is a mobile or a branch (mobile? and branch?)
