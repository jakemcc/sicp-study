; Exercise 3.5

(use 'clojure.contrib.math)

(defn monte-carlo [trials experiment]
 (loop [trials-remaining trials
        trials-passed 0]
   (cond (= trials-remaining 0) (/ trials-passed trials)
         (experiment) (recur (dec trials-remaining) (inc trials-passed))
         :else (recur (dec trials-remaining) trials-passed))))

(defn random-in-range [low high]
 (+ low (rand (- high low))))

(defn rectangle-area [x1 x2 y1 y2]
 (abs (* (- x2 x1) (- y2 y1))))

(defn estimate-integral [p x1 x2 y1 y2 trials]
 (letfn
  [(experiment []
    (let [x (random-in-range x1 x2)
          y (random-in-range y1 y2)]
     (p x y)))]
  (* (rectangle-area x1 x2 y1 y2)
     (monte-carlo trials experiment))))

(defn square [x] (expt x 2))

(defn estimate-pi-using-area-unit-circle []
 (letfn [(unit-circle-predicate [x y] 
          (<= (+ (square x) (square y)) 1))]
 (double 
  (estimate-integral
   unit-circle-predicate -1.0 1.0 -1.0 1.0 10000))))


(time (dotimes [i 5]
 (println "Pi estimate =" (estimate-pi-using-area-unit-circle))))
