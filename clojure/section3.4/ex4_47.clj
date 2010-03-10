; Exercise 4.47.

; a) Write a semaphore in terms of mutex defined in book.

(defn test-and-set!
  [cell]
  (if @cell
      true
      (do (swap! cell (fn [x] true))
	  false)))

(defn clear! [cell]
  (swap! cell (fn [x] false)))

(defn make-mutex
  []
  (let [cell (atom false)
	(fn the-mutex [m]
	  (cond (= m :aquire)
		(if (test-and-set! cell)
		    (recur :aquire))
		(= m :release) (clear! cell)))]
    the-mutex))

(defn make-semaphore
  [n]
  (let [count (atom n)
	mutex (make-mutex)]
    (letfn [
	    (fn aquire []
	      (loop [still-need true]
		(mutex :release)
		(while still-need
		       (mutex :aquire)
		       (if (> 0 @count)
			 (do (swap! count dec)
			     (recur false))
			 (recur true)))))
	    (fn release []
	      (mutex :aquire)
	      (if (< n @count) (swap! count inc))
	      (mutex :release))
	    (fn dispatch [m]
	      (cond (= m :aquire) (aquire)
		    (= m :release) (release)))]
      dispatch)))

; b) Do it using test-and-set!

(defn loop-test-and-set!
  [cell]
  (if (test-and-set! cell)
      (recur cell)))

(defn make-semaphore
  [n]
  (let [count (atom n)
        cell (atom false)]
    (letfn [(fn aquire []
	      (loop [still-need true]
		(clear! cell)
		(while still-need
		       (loop-test-and-set! cell)
		       (if (> 0 @count)
			 (do (swap! count dec)
			     (recur false))
			 (recur true)))))
	    (fn release []
	      (loop-test-and-set! cell)
	      (if (< n @count) (swap! count inc))
	      (clear! cell))
	    (fn dispatch [m]
	      (cond (= m :aquire) (aquire)
		    (= m :release) (release)))]
      dispatch))