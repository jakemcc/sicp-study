; Exercise 3.39

; Potential before change: 101, 121, 110, 11, 100

; Which of the above 5 remain after code change?

; 101 -> first lambda sets x to 100, second increments by 1
; 121 -> second lambda sets x to 11, first squares to get 121.
; 100 -> first lambda squares x, but before it sets it other lambda
;        does its work, which is then lost when first lambda sets to 100
