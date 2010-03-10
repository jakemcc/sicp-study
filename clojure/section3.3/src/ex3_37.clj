
(ns ex3-37
  (:use constraint-system))

(defn c+ [x y]
  (let [z (make-connector)]
    (make-adder x y z)
    z))

(defn c- [x y]
  (let [z (make-connector)]
    (make-adder y z x)
    z))

(defn c* [x y]
  (let [z (make-connector)]
    (make-multiplier x y z)
    z))

(defn cd [x y]
  (let [z (make-connector)]
    (make-multiplier y z x)
    z))

(defn cv [x]
  (let [z (make-connector)]
    (make-constant x z)
    z))

