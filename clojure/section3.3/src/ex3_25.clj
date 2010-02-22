; Exercise 3.25

(ns ex3_25
 (:require [localtable :as lt]))

; Simply wrap version of table which takes two keys and pass in
; list as first key and dummy value as second.

(defn make-table []
 (let [local-table (lt/make-table)]
  (letfn [(lookup [kees] ((local-table :lookup-proc) kees :zzz))
          (insert [kees value] ((local-table :insert-proc!) kees :zzz value))]
  (fn [m]
   (cond (= m :lookup-proc) lookup 
         (= m :insert-proc!) insert
         :else (Error. "Unknown operation -- TABLE" m))))))
