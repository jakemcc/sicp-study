; Exercise 3.22
(ns ex3_22
 (:use mypair))

(defn make-queue []
 (let [front-ptr (atom :empty)
       rear-ptr (atom :empty)]
  (letfn [(set-front-ptr! [x] (swap! front-ptr (fn [y] x)))
          (set-rear-ptr! [x] (swap! rear-ptr (fn [y] x)))
          (set-front-and-rear-ptr! [x]
           (set-front-ptr! x)
           (set-rear-ptr! x))
          (empty-queue? []
           (and (= :empty @front-ptr) (= :empty @rear-ptr)))
          (front []
           (if (empty-queue?) (Error. "FRONT called with empty queue")
               (car @front-ptr)))
          (insert! [x]
           (let [e (make-pair x)]
            (if (empty-queue?)
                (do (set-front-and-rear-ptr! e) dispatch)
                (do (set-cdr! @rear-ptr e)
                    (set-rear-ptr! e)
                    dispatch))))
          (delete! []
           (if (empty-queue?)
               (Error. "DELETE! called with empty queue")
               (if (= @front-ptr @rear-ptr)
                   (do (set-front-and-rear-ptr! :empty) dispatch)
                   (do (set-front-ptr! (cdr @front-ptr)) dispatch))))
          (dispatch
           ([action]
            (cond (= action :front) (front)
                  (= action :delete!) (delete!)
                  (= action :empty?) (empty-queue?)
                  :else (Error. (str action "is not supported"))))
           ([action e]
            (cond (= action :insert!) (insert! e)
                  :else (Error. (str action "is not supported")))))]
          dispatch)))

; Look at test/test_ex3_22 for examples of how to use
