; Exercise 2.22

; Louis Reasoner trieds to make square-list iterative.
; In the process he made it return the result list in
; reverse order.
;
;
; What is wrong with the Louis Reasoner's first attempt? (below)

(defn square [x] (* x x))

(defn square-list [items]
 (letfn [(iter [things answer]
          (if (empty? things)
              answer
              (recur (rest things)
                     (cons (square (first things))
                           answer))))]
  (iter items nil)))

(square-list (list 1 2 3 4))

; The issue is that he is building up the answer list by pulling
; elements off the front of the input list. This is causing the resulting
; list to be built backwards as elements originally further back in the
; input list are put onto the front of the answer list

; In Louis Reasoner's second attempt he switched the order of the arguments to cons.
; (To do this in clojure needed to change cons to list call.
; This causes a list of lists which is not the desired result

(defn square-list [items]
 (letfn [(iter [things answer]
          (if (empty? things)
              answer
              (recur (rest things)
                     (list answer (square (first things))))))]
  (iter items nil)))

(println (square-list (list 1 2 3 4)))
