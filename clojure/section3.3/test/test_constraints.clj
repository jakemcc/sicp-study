
(ns test-constraints
  (:use constraint-system
	clojure.test))

(deftest connectors-with-no-value-has-no-value
  (let [connector (make-connector)]
    (is (= false (has-value? connector)))))

(deftest connector-with-value-has-value
  (let [connector (make-connector)]
    (set-value! connector 100 :user)
    (is (= true (has-value? connector)))
    (is (= 100 (get-value connector)))))

(deftest connector-forgets-value
  (let [connector (make-connector)]
    (set-value! connector 100 :user)
    (is (= true (has-value? connector)))
    (forget-value! connector :user)
    (is (= false (has-value? connector)))))

(deftest making-adder-has-no-side-affects
   (let [a1 (make-connector)
	 a2 (make-connector)
	 sum (make-connector)
	 adder (make-adder a1 a2 sum)]
     (is (= false (has-value? a1)))
     (is (= false (has-value? a2)))
     (is (= false (has-value? sum)))))

(deftest adder-works
   (let [a1 (make-connector)
	 a2 (make-connector)
	 sum (make-connector)
	 adder (make-adder a1 a2 sum)]
     (set-value! a1 5 :user)
     (set-value! a2 4 :user)
     (is (= 9 (get-value sum)))
     (forget-value! a1 :user)
     (is (= 5 (get-value a1)))
     (forget-value! a2 :user)
     (is (= 4 (get-value a2)))
     (forget-value! sum :user)
     (is (= false (has-value? sum)))
     (set-value! a2 7 :user)
     (set-value! a1 10 :user)
     (is (= 17 (get-value sum)))))

(deftest making-multiplier-has-no-side-affects
   (let [m1 (make-connector)
	 m2 (make-connector)
	 product (make-connector)
	 multiplier (make-multiplier m1 m2 product)]
     (is (= false (has-value? m1)))
     (is (= false (has-value? m2)))
     (is (= false (has-value? product)))))

(deftest multiplier-works
   (let [m1 (make-connector)
	 m2 (make-connector)
	 product (make-connector)
	 multiplier (make-multiplier m1 m2 product)]
     (set-value! m1 5 :user)
     (set-value! m2 4 :user)
     (is (= 20 (get-value product)))
     (forget-value! m1 :user)
     (is (= 5 (get-value m1)))
     (forget-value! m2 :user)
     (is (= 4 (get-value m2)))
     (forget-value! product :user)
     (is (= false (has-value? product)))
     (set-value! m1 10 :user)
     (set-value! m2 7 :user)
     (is (= 70 (get-value product)))))

(deftest constant-always-has-value
  (let [constant (make-constant 7 (make-connector))]
    (is (= true (has-value? constant)))))

(deftest can-get-constant-value
  (let [constant (make-constant 7 (make-connector))]
    (is (= 7 (get-value constant)))))
