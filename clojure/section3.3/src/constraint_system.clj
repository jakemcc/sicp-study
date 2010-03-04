
(ns constraint-system)

(defprotocol Constraint
  (process-new-value [this])
  (process-forget-value [this]))

(defprotocol Connection
  (has-value? [connection])
  (get-value [connection])
  (set-value! [connection new-value setter])
  (forget-value! [connection retractor])
  (connect [connection new-constraint]))

(defn inform-about-value
  [constraint]
  (process-new-value constraint))

(defn inform-about-no-value
  [constraint]
  (process-forget-value constraint))

(defn for-each-except
  [exception procedure ls]
  (loop [items ls]
    (cond (empty? items) :done
	  (= (first items) exception) (recur (rest items))
	  :else (do
		  (procedure (first items))
		  (recur (rest items))))))

(deftype Constant [connector] :as this
  Connection
  (has-value? [] true)
  (get-value [] (get-value connector))
  (set-value! [new-value setter] (Error. "Unknown request -- CONSTANT"))
  (forget-value! [retractor] (Error. "Unknown request -- CONSTANT"))
  (connect [new-constraint] (Error. "Unknown request -- CONSTANT")))

(defn make-constant [value connector]
  (let [constant (Constant connector)]
    (connect connector constant)
    (set-value! connector value constant)
    constant))

(deftype Connector [value informant constraints] :as this
  Connection
  (has-value? [] (if @informant true false))

  (get-value [] @value)

  (set-value! [new-value setter]
    (cond (not (has-value? this))
	  (do (swap! value (fn [x] new-value))
	      (swap! informant (fn [x] setter))
	      (for-each-except setter
			       inform-about-value
			       @constraints))
	  (not (= @value new-value))
	  (println (str "Contradiction! (" @value " " new-value ")"))
	  :else :ignored))

  (forget-value! [retractor]
    (if (= retractor @informant)
        (do (swap! informant (fn [x] false))
	    (for-each-except retractor
			     inform-about-no-value
			     @constraints))
	:ignored))

  (connect [new-constraint]
    (if (not (some #{new-constraint} @constraints))
        (swap! constraints #(cons new-constraint %)))
    (if (has-value? this)
        (inform-about-value new-constraint))
    :done))

(defn make-connector []
  (Connector (atom false) (atom false) (atom '())))

(deftype Multiplier [m1 m2 product]
  :as this
  Constraint
  (process-new-value
   []
   (cond (or (and (has-value? m1) (zero? (get-value m1)))
	     (and (has-value? m2) (zero? (get-value m2))))
	 (set-value! product 0 this)
	 (and (has-value? m1) (has-value? m2))
	 (set-value! product
		     (* (get-value m1) (get-value m2))
		     this)
	 (and (has-value? product) (has-value? m1))
	 (set-value! m2
		     (/ (get-value product) (get-value m1))
		     this)
	 (and (has-value? product) (has-value? m2))
	 (set-value! m1
		     (/ (get-value product) (get-value m2))
		     this)))

  (process-forget-value
   []
   (forget-value! product this)
   (forget-value! m1 this)
   (forget-value! m2 this)
   (process-new-value this)))

(defn make-multiplier [m1 m2 product]
  (let [multiplier (Multiplier m1 m2 product)]
    (connect m1 multiplier)
    (connect m2 multiplier)
    (connect product multiplier)))

(deftype Adder [a1 a2 sum] :as this
  Constraint
  (process-new-value
   []
   (cond (and (has-value? a1) (has-value? a2))
	 (set-value! sum
		     (+ (get-value a1) (get-value a2))
		     this)
	 (and (has-value? a1) (has-value? sum))
	 (set-value! a2
		     (- (get-value sum) (get-value a1))
		     this)
	 (and (has-value? a2) (has-value? sum))
	 (set-value! a1
		     (- (get-value sum) (get-value a2))
		     this)))

  (process-forget-value
   []
   (forget-value! a1 this)
   (forget-value! a2 this)
   (forget-value! sum this)
   (process-new-value this)))

(defn make-adder [a1 a2 sum]
  (let [adder (Adder a1 a2 sum)]
    (connect a1 adder)
    (connect a2 adder)
    (connect sum adder)))

(defn print-probe [name value]
  (println "Probe: " name " = " value))

(deftype Probe [name connector] :as this
  Constraint
  (process-new-value
   []
   (print-probe name (get-value connector)))
  (process-forget-value
   []
   (print-probe name "?")))

(defn make-probe [name connector]
  (let [probe (Probe name connector)]
    (connect connector probe)
    probe))

