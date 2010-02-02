(ns test_queue
 (:use queue
       clojure.test))

(deftest should-detect-empty
 (= true (empty-queue? (make-queue))))

(deftest can-insert-and-remove
 (let [q (make-queue)]
  (insert-queue! q :a)
  (is (= :a (front-queue q)))
  (delete-queue! q)
  (is (= true (empty-queue? q)))
  (insert-queue! (insert-queue! q :b) :c)
  (is (= :b (front-queue q)))
  (delete-queue! q)
  (is (= :c (front-queue q)))))

; Exercise 3.21
(deftest can-print-empty-queue
 (is (= "()" (get-queue-str (make-queue)))))

(deftest can-print-queue
 (is (= "(:a :b :c)" (-> (make-queue)
                         (insert-queue! :a)
                         (insert-queue! :b)
                         (insert-queue! :c)
                         get-queue-str))))
                         
(deftest after-printing-queue-still-intact
 (let [q (-> (make-queue) (insert-queue! :a) (insert-queue! :b))]
  (get-queue-str q)
  (is (= :a (front-queue q)))
  (delete-queue! q)
  (is (= :b (front-queue q)))))
