; Exercise 2.25
;
;
; Give the combination of car and cdrs (first and rest) that will pick 7 from the following


; (1 3 (5 7) 9)
(first (rest (first (rest (rest '(1 3 (5 7) 9))))))
;
; ((7))
(first (first '((7))))
;
;
; (1 (2 (3 (4 (5 (6 7))))))
(first 
 (rest 
  (first 
   (rest 
    (first 
     (rest 
      (first 
       (rest 
        (first 
         (rest 
          (first 
           (rest '(1 (2 (3 (4 (5 (6 7))))))))))))))))))
