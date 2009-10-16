; Exercise 1.4 

; Draw graph for (count-change 11)
; Code from book pulled out and translated into Clojure

(defn first-denomination [kinds-of-coins]
 (cond (= kinds-of-coins 1) 1
       (= kinds-of-coins 2) 5
       (= kinds-of-coins 3) 10
       (= kinds-of-coins 4) 25 
       (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
 (cond (= amount 0) 1
       (or (< amount 0) (= kinds-of-coins 0)) 0
       :else (+ (cc amount
                    (- kinds-of-coins 1))
                (cc (- amount
                       (first-denomination kinds-of-coins))
                    kinds-of-coins))))

(defn count-change [amount]
 (cc amount 5))

(count-change 100)
; -> 292

(count-change 11)

; (count-change 11)
; (cc 11 5)
; (+ (cc 11 4) (cc -39 5))
; (+ (+ (cc 11 3) (cc -14 4)) 0)
; (+ (+ (+ (cc 11 2) (cc 1 3)) 0) 0)
; (+ (+ (+ (+ (cc 11 1) (cc 6 2)) (+ (cc 1 2) (cc -4 3))) 0) 0)
; (+ (+ (+ (+ (+ (cc 11 0) (cc 10 1)) (+ (cc 6 1) (cc 1 2)) (+ (+ (cc 1 1) (cc -4 2)) 0))) 0) 0)
; (+ (+ (+ (+ (+ 0 (+ (cc 10 0) (cc 9 1))) (+ (+ (cc 6 0) (cc 5 1)) (+ (cc 1 1) (cc -4 2))) (+ (+ (+ (cc 1 0) (cc 0 1)) 0) 0))) 0) 0) 
; (+ (+ (+ (+ (+ 0 (+ 0 (+ (cc 9 0) (cc 8 1))) (+ (+ 0 (+ (cc 5 0) (cc 4 1))) (+ (+ (cc 1 0) (cc 0 1)) 0)    1 ))))))  
; SKIPPED SOME STEPS HERE, ANY LINE WITH KINDS-OF-COINS == 1 REDUCES TO 1 
; (+ (+ (+ (+ (+ 0 (+ 0 (+ 0 1)) (+ (+ 0(+ 0 1))) (+ (+ 0 1) 0) 1)))))
; 4
;
; space growth = O(amount)
; Reason: Space grows linearly since deepest part of tree is when it is taking 1 sent off a turn.
; Time growth = Terrible.
