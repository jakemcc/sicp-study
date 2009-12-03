; Exercise 2.60
;
; Suppose we change the representation of sets to be
; lists which allow duplicate entries.  What do element-of-set?,
; adjoin-set, union-set, and intersection-set look like?
; How does the efficiency compare?
; Would you ever want to use this representation



; element-of-set? does not need to change
(defn adjoin-set [x s] (cons x s))
(defn union-set [s1 s2] (concat s1 s2))
; Intersection can still be the same as the way in the book

; Efficiency?
; element-of-set? is less efficient if searching for an element which is not in the list.
; Overall it is still O(n) where n is the number of elements in the set though
;
; adjoin-set no longer uses element-of-set?, it simply puts the element on the list.
; Now is O(1)
;
; union-set is whatever concat is, hopefully O(n)
;
; intersection is the same as it was O(n^2)
;
; This representation might be useful if union-set and adjoin-set were the
; most common operations one was performing.


