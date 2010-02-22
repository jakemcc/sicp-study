
(ns test_ex3_25
 (:use ex3_25
       clojure.test))

(deftest can-use-list-as-key
 (let [tab (make-table)]
  ((tab :insert-proc!) '(:a :b) :jake)
  ((tab :insert-proc!) '(:a :b :e) :mike)
  (is (= :mike ((tab :lookup-proc) '(:a :b :e))))
  (is (= :jake ((tab :lookup-proc) '(:a :b))))))
