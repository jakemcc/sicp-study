
(ns celsius-fahrenheit-converter
  (:use constraint-system
	clojure.test))

(defn celsius-fahrenheit-converter
  [c f]
  (let [u (make-connector)
	v (make-connector)
	w (make-connector)
	x (make-connector)
	y (make-connector)]
    (make-multiplier c w u)
    (make-multiplier v x u)
    (make-adder v y f)
    (make-constant 9 w)
    (make-constant 5 x)
    (make-constant 32 y)
    :ok))

(def C (make-connector))
(def F (make-connector))
(celsius-fahrenheit-converter C F)

(deftest setting-fahrenheit-gives-celsius
  (set-value! F 77 :user)
  (is (= 25 (get-value C)))
  (forget-value! F :user))

(deftest setting-celsius-gives-fahrenheit
   (set-value! C 100 :user)
   (is (= 212 (get-value F)))
   (forget-value! C :user))

; Using probes as in book instead of tests
(make-probe "Celsius temp" C)
(make-probe "Fahrenheit temp" F)
(set-value! C 25 :user)
(set-value! F 212 :user)
(forget-value! C :user)
(set-value! F 212 :user)
(forget-value! C :user)
(forget-value! F :user)


