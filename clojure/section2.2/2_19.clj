; Exercise 2.19

; Make changes the change-counting program of section 1.2.2 so
; a list of potential coins can be supplied instead of it
; only working wiith U.S. coins

; Want the following behavior
; (def us-coins (list 50 25 10 5 1))
; (def uk-coins (list 100 50 20 10 5 2 1 0.5))
; (cc 100 us-coins)
; 293

; Implement first-denomination, except-first-denomination and no-more? s
; the following cc procedure works
(defn cc [amount coin-values]
 (cond (= amount 0) 1
       (or (< amount 0) (no-more? coin-values)) 0
       :else
         (+ (cc amount
                (except-first-denomination coin-values))
            (cc (- amount
                   (first-denomination coin-values))
                coin-values))))

(defn first-denomination [x]
 (first x))

(defn except-first-denomination [x]
 (rest x))

(defn no-more? [x]
 (empty? x))
    
(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))
(cc 100 us-coins)
(cc 50 uk-coins)

; Does the order of the list of coins affect answer produced by cc?
; No.  It simply affects the order in which solutions are found.
