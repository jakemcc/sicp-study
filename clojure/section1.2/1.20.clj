; Exercise 1.20
;

(defn remainder [a b]
 (mod a b))

(defn gcd [a b]
 (if (= b 0)
     a
     (gcd b (remainder a b))))

; How many times is the procedure remainder called if (gcd 206 40) is evaluated and normal-order evaluation is used?
;
; (gcd 206 40)
; (gcd 40 (remainder 206 40)) 
;       -> causes remainder to be called 1 time
; (gcd (remainder 206 40) (remainder 40 (remainder 206 40))) 
;       -> causes remainder to be called 2 times
; (gcd (remainder 40 (remainder 206 40)) (remainder (remainder 206 40) (remainder 40 (remainder 206 40))))
;       -> causes remainder to be called 4 times 
; (gcd (remainder (remainder 206 40) (remainder 40 (remainder 206 40))) (remainder (remainder 40 (remainder 206 40)) (remainder (remainder 206 40) (remainder 40 (remainder 206 40)))))
;       -> causes remainder to be called 11 times, reason is said below
; If statement evaluates (remainder (remainder 40 (remainder 206 40)) (remainder (remainder 206 40) (remainder 40 (remainder 206 40)))) and gets 0 which causes a to be evaluated (remainder (remainder 206 40) (remainder 40 (remainder 206 40)))
; 11 + 4 + 2 + 1 = 18 calls 
;
; How many times if applicative-order evaluation is used?
; 
; (gcd 206 40)
; (gcd 40 (remainder 206 40)) -> (gcd 40 6)     [ 1 called to remainder ]
; (gcd 6 (remainder 40 6))    -> (gcd 6 4)      [ 1 called to remainder ]
; (gcd 4 (remainder 6 4))     -> (gcd 4 2)      [ 1 called to remainder ]
; (gcd 2 (remainder 4 2))     -> (gcd 2 0)      [ 1 called to remainder ]
; Total number of calls = 4
