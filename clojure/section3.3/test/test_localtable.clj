
(ns test_localtable
  (:use localtable
        clojure.test))

(deftest false-for-keys-not-found
  (let [tab (make-table)]
    (is (= false ((tab :lookup-proc) '(:a :b))))))

(deftest can-insert-and-find-one
  (let [tab (make-table)]
    ((tab :insert-proc!) '(:a :b) :jake)
    (is (= :jake ((tab :lookup-proc) '(:a :b))))))

;(deftest can-insert-and-find-with-variable-num-keys
;  (let [tab (make-table)]
;    ((tab :insert-proc!) '(:c) :mike)
;    ((tab :insert-proc!) '(:a :b) :jake)
;    ;((tab :insert-proc!) '(:b :c :a) :jeff)
;    (is (= :mike ((tab :lookup-proc) '(:c))))
;    ;(is (= :jeff ((tab :lookup-proc) '(:b :c :a))))
;    (is (= :jake ((tab :lookup-proc) '(:a :b))))))

(deftest can-overwrite-entries
  (let [tab (make-table)]
    ((tab :insert-proc!) '(:a :b) :jake)
    ((tab :insert-proc!) '(:a :b) :mike)
    (is (= :mike ((tab :lookup-proc) '(:a :b))))))

(deftest table-supports-multiple-entries
  (let [tab (make-table)]
    ((tab :insert-proc!) '(:a :b) :jake)
    ((tab :insert-proc!) '(:a :c) :mccrary)
    ((tab :insert-proc!) '(:b :a) :ekaj)
    (is (= :jake ((tab :lookup-proc) '(:a :b))))
    (is (= :mccrary ((tab :lookup-proc) '(:a :c))))
    (is (= :ekaj ((tab :lookup-proc) '(:b :a))))))

(deftest can-supply-own-key-compare
 (let [tab (make-table (fn [x y] true))]
    ((tab :insert-proc!) '(:a :b) :jake)
    (is (= :jake) ((tab :lookup-proc) '(:not :keys)))))


