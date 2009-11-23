; Exercise 2.35
; not working
(use 'clojure.test)

;; (defn accumulate [op initial sq]
;;  (if (empty? sq)
;;      initial
;;      (op (first sq)
;;          (accumulate op initial (rest sq)))))
;; 
;; (defn count-leaves [t]
;;  (accumulate (fn [x y] (inc y)) 0 (map count t)))
;; 
;; (deftest test-count-leaves
;;  (is (= 3 (count-leaves '(1 (2 3))))))
;; 
;; (run-tests)
