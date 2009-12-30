; Exercise 2.78

; Modify below attach-tag, type-tag, and contents from section 2.4.2
; to work without having to tag our 'scheme-number type with 'scheme-number.
; Use built-in functions to identify numbers instead.

(use 'clojure.test)

(defn attach-tag [tt contents]
 (if (number? contents) contents
     (cons tt contents)))

(defn type-tag [datum]
 (cond (number? datum) 'scheme-number
       (seq? datum) (first datum)
       :else (Error. (str "Bad tagged datum -- TYPE-TAG " datum))))

(defn contents [datum]
 (cond (number? datum) datum
       (seq? datum) (rest datum)
       :else (Error. (str "Bag tagged datum -- CONTENTS " datum))))

(deftest test-attach-tag
 (is (= 4 (attach-tag 'scheme-number 4)))
 (is (= '(rational 3 5) (attach-tag 'rational '(3 5)))))

(deftest test-type-tag
 (is (= 'scheme-number (type-tag 4)))
 (is (= 'rational (type-tag '(rational 3 5)))))

(deftest test-contents
 (is (= 4 (contents 4)))
 (is (= '(3 5) (contents '(rational 3 5)))))

(run-tests)
