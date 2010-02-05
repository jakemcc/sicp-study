(ns test-deque
  (:use clojure.test
	deque))

(deftest new-qeue-should-be-empty
  (is (= true (empty-deque? (make-deque)))))

(deftest can-insert-single-element-front
  (is (= :a (-> (make-deque)
	        (front-insert-deque! :a)
		(front-deque))))
  (is (= :a (-> (make-deque)
	        (front-insert-deque! :a)
		(rear-deque)))))

(deftest can-insert-single-element-rear
  (is (= :a (-> (make-deque)
	        (rear-insert-deque! :a)
		(front-deque))))
  (is (= :a (-> (make-deque)
	        (rear-insert-deque! :a)
		(rear-deque)))))

(deftest can-insert-multiple-elements
  (let [d (-> (make-deque)
	      (rear-insert-deque! :b)
	      (front-insert-deque! :a)
	      (front-insert-deque! :c))]
	(is (= :c (front-deque d)))
	(is (= :b (rear-deque d)))))

(deftest can-remove-from-front
  (let [d (-> (make-deque)
	      (front-insert-deque! :b)
	      (front-insert-deque! :a))]
    (front-delete-deque! d)
    (is (= :b (front-deque d)))
    (front-delete-deque! d)
    (is (= true (empty-deque? d)))))

(deftest can-remove-from-rear
  (let [d (-> (make-deque)
	      (front-insert-deque! :b)
	      (front-insert-deque! :a))]
    (rear-delete-deque! d)
    (is (= :a (rear-deque d)))
    (rear-delete-deque! d)
    (is (= true (empty-deque? d)))))