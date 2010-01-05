; Exercise 3.6
;
; Create a random function which generates a psuedo-random
; sequence which can be reset

(use 'clojure.test)

(defn- rand-update [x]
 (let [a 5.2 b 3.1 m 1.5]
  (mod (+ (* a x) b) m)))

(def current-random (atom 5.0))

(defn my-rand
 ([] 
  (swap! current-random rand-update)
  @current-random)
 ([action]
  (if (= action :generate) (my-rand)))
 ([action seed]
  (if (= action :reset) (swap! current-random (fn [x] seed)))))


(deftest test-reset-random-sequence
 (my-rand :reset 6.0)
 (let [orig (list (my-rand :generate)
                  (my-rand :generate)
                  (my-rand :generate))]
  (my-rand :reset 6.0)
  (let [after-reset (list (my-rand :generate)
                          (my-rand :generate)
                          (my-rand :generate))]
   (is (= orig after-reset)))))

(run-tests)
