
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
                (lookup [& kys]
                  (let [subtable (my-assoc (first kys) (cdr local-table))]
                    (lookup-internal (next kys) subtable)))
                (lookup-internal [kys cur]
                  (cond (and kys cur)
                          (let [rec (my-assoc (first kys) (cdr cur))]
                            (lookup-internal (next kys) rec))
                        (and (nil? kys) cur) (cdr cur)
                        (not cur) false))
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
