
(ns test-ex3-37
  (:use ex3-37
	constraint-system
	clojure.test))

(defn celsius-fahrenheit-converter [x]
  (c+ (c* (cd (cv 9) (cv 5))
	  x)
      (cv 32)))

(def C (make-connector))
(def F (celsius-fahrenheit-converter C))

(deftest can-convert-celius-to-fahrenheit
  (set-value! C 100 :user)
  (is (= 212 (get-value F))))
