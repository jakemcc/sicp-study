(ns queue
 (:use mypair))

(defn make-queue []
 (make-pair :empty :empty))

(defn empty-queue? [q]
 (and (= (car q) :empty)
      (= (cdr q) :empty)))

(defn front-ptr [q] (car q))
(defn rear-ptr [q] (cdr q))
(defn set-front-ptr! [q i] (set-car! q i))
(defn set-rear-ptr! [q i] (set-cdr! q i))

(defn set-front-and-rear-ptr! [q x]
 (set-front-ptr! q x)
 (set-rear-ptr! q x))

(defn front-queue [q]
 (if (empty-queue? q)
     (Error. "FRONT called with empty queue")
     (car (front-ptr q))))

(defn insert-queue!
 [queue x]
 (let [e (make-pair x)]
  (if (empty-queue? queue)
      (do (set-front-and-rear-ptr! queue e) queue)
      (do (set-cdr! (rear-ptr queue) e)
          (set-rear-ptr! queue e)
          queue))))

(defn delete-queue!
 [queue]
 (if (empty-queue? queue)
     (Error. "DELETE! called with empty queue")
     (let [front (car queue)]
      (if (= front (cdr queue))
          (do (set-front-and-rear-ptr! queue :empty) queue)
          (do (set-front-ptr! queue (cdr front)) queue)))))

; Exercise 3.21
(defn get-queue-str [queue]
 (let [backup (atom @queue), rear (rear-ptr queue)]
   (str "("
        (loop [middle ""]
         (if (empty-queue? backup) middle
             (do
              (let [m (str middle " " (front-queue backup))]
               (delete-queue! backup)
               (recur m)))))
        ")")))
           
      



