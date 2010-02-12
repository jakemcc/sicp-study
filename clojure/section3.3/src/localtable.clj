
(ns localtable
  (:use mypair))

(defn make-table
  ([] (make-table =))
  ([same-key?]
      (let [local-table (my-list :*table*)]
        (letfn [
                (my-assoc [key1 records]
                  (cond (nil? records) false
                        (same-key? key1 (car (car records))) (car records)
                        :else (my-assoc key1 (cdr records))))
                    (lookup [key-1 key-2]
                  (let [subtable (my-assoc key-1 (cdr local-table))]
                    (if subtable
                        (let [record (my-assoc key-2 (cdr subtable))]
                          (if record
                              (cdr record)
                              false))
                        false)))
                (insert! [key-1 key-2 value]
                  (let [subtable (my-assoc key-1 (cdr local-table))]
                    (if subtable
                        (let [record (my-assoc key-2 (cdr subtable))]
                          (if record
                              (set-cdr! record value)
                              (set-cdr! subtable
                                        (make-pair (make-pair key-2 value)
                                                   (cdr subtable)))))
                        (set-cdr! local-table
                                  (make-pair (my-list key-1
                                                      (make-pair key-2 value))
                                             (cdr local-table)))))
                  :ok)
                (dispatch [m]
                  (cond (= m :lookup-proc) lookup
                        (= m :insert-proc!) insert!
                        (= m :table) local-table
                        :else (Error. "Unknown operation -- TABLE" m)))]
            dispatch))))
