; Exercise 2.31

(use 'clojure.test)
(defn subsets [s]
  (if (empty? s)
      (list nil)
      (let [rst (subsets (rest s))]
        (concat rst (map (fn [x] (cons (first s) x)) rst)))))

(subsets '(1 2 3))

(deftest subsets-works-with-example-from-book
 (is (= '(() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3)))))

(run-tests)

; expected '( () (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3))
;
; This solution works the following way:
; 1) Take a set (from here called orig-set) and take away every element of it 
;        -> this leaves '(()) which I will call new-set from now on.
; 2) Take one element element from orig-set and add it to new-set along
;    with add it concated with any element that was previously in new-set
; 3) Repeat step two till every element in orig-set has been choosen
; 4) You are left with new-set having every combination of elements from
;    orig-set

