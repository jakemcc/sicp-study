; Exercise 3.8
;
; Design a function which will show order arguments are evaluated

; First pass, this does work
(def #^{:private true} flag (atom 5))
(defn f [x]
 (if (compare-and-set! flag 5 x) 0
   (if (= 0 @flag) 0
       1)))

; Prints 0 if arguments evaluated left to right
; and 1 if arguments evaluated right to left
(println (+ (f 0) (f 1)))


; Second shot
(def f
 (let [check (atom 1)]
  (fn [x]
   (swap! flag #(* % x))
   @flag)))
(println (+ (f 0) (f 1)))
