; Exercise 2.54
;
; Two lists are said to be equal? if they contain
; equal elements arranged in the same order. Implement equal? for 
; lists of symbols

(use 'clojure.test)

(def equal? =)

(deftest equal?-works
 (is (= true (equal? '(a b c) '(a b c))))
 (is (= false (equal? '(a b) '(a b z))))
 (is (= false (equal? '(b a c) '(b a d)))))

(run-tests)

(defn equal? [x y]
 "equal? defined using recursion instead of cheating and using ="
 (cond (and (not (seq? x)) (not (seq? y))) (= x y)
       (empty? x) (empty? y)
       (empty? y) (empty? x)
       :else (and (equal? (first x) (first y))
                  (equal? (rest x) (rest y)))))

(run-tests)
