; Exercise 3.23
(ns deque)

(declare make-deque
	 empty-deque?
	 front-deque
	 rear-deque
	 front-insert-deque!
	 rear-insert-deque!
	 front-delete-deque!
	 rear-delete-deque!)

(defn make-deque []
  (atom {:front :empty :rear :empty}))

(defn empty-deque? [d]
  (and (= :empty (:front @d))
       (= :empty (:rear @d))))

(defn set-front [d e]
  (swap! d assoc :front e))

(defn set-rear [d e]
  (swap! d assoc :rear e))

(defn rear [d]
  (:rear @d))
(defn front [d]
  (:front @d))

(defn set-prev [n e]
  (swap! n assoc :prev e))

(defn set-next [n e]
  (swap! n assoc :next e))

(defn make-neighbors
  [prev next]
  (set-prev next prev)
  (set-next prev next))

(defn get-elem [n]
  (:elem @n))
(defn get-next [n]
  (:next @n))
(defn get-prev [n]
  (:prev @n))

(defn make-node [e]
  (atom {:elem e :prev nil :next nil}))

(defn add-first-elem [d e]
  (let [node (make-node e)]
    (set-front d node)
    (set-rear d node)))

(defn rear-deque [d]
  (if (empty-deque? d) (Error. "REAR-DEQUE called on empty queue")
      (get-elem (rear d))))

(defn front-deque [d]
  (if (empty-deque? d) (Error. "FRONT-DEQUE called on empty queue")
      (get-elem (front d))))

(defn front-insert-deque! [d e]
  (if (empty-deque? d) (add-first-elem d e)
      (let [node (make-node e)
	    prev-front (front d)]
	(make-neighbors node prev-front)
	(set-front d node)))
  d)

(defn rear-insert-deque! [d e]
  (if (empty-deque? d) (add-first-elem d e)
      (let [node (make-node e)
	    prev-rear (rear d)]
	(make-neighbors prev-rear node)
	(set-rear d node)))
  d)

(defn empty! [d]
  (set-front d :empty)
  (set-rear d :empty))

(defn front-delete-deque! [d]
  (cond (empty-deque? d) (Error. "Already empty")
	(= (front d) (rear d)) (empty! d)
	:else (set-front d (get-next (front d)))))

(defn rear-delete-deque! [d]
  (cond (empty-deque? d) (Error. "Already empty")
	(= (front d) (rear d)) (empty! d)
	:else (set-rear d (get-prev (rear d)))))

	

