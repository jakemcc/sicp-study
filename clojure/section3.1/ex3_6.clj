; Exercise 3.6
;
; Create a random function which generates a psuedo-random
; sequence which can be reset

(use 'clojure.test)

(defn rand-update [x]
 (let [a 5.2 b 3.1 m 1.5]
  (mod (+ (* a x) b) m)))

(defn get-random-sequence []
 (let [initial-val 5.0
       current (atom initial-val)]
  (fn [action]
   (if (= action :reset) (swap! current (fn [x] initial-val))
       (do
         (swap! current rand-update)
         @current)))))


(deftest test-reset-random-sequence
 (let [random-seq (get-random-sequence)
       orig (list (random-seq :generate)
                  (random-seq :generate)
                  (random-seq :generate))]
  (random-seq :reset)
  (let [after-reset (list (random-seq :generate)
                          (random-seq :generate)
                          (random-seq :generate))]
   (is (= orig after-reset)))))

(run-tests)
