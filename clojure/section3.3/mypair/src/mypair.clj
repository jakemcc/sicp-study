(ns mypair)

(defstruct pair :car :cdr)
(defn pair? [p]
 (= clojure.lang.Atom (type p)))

(defn make-pair 
 ([] (make-pair nil nil))
 ([a] (make-pair a nil))
 ([a b] (atom (struct pair a b))))

(defn set-car! [x p]
 (swap! p #(assoc % :car x))) 

(defn set-cdr! [x p]
 (swap! p #(assoc % :cdr x))) 

(defn car [p]
 (:car @p))

(defn cdr [p]
 (:cdr @p))

(defn my-cons [p1 p2]
 (make-pair
  (car p1)
  p2))

(defn my-list [& elems]
 (loop [xs (reverse elems), res (make-pair)]
  (if (nil? xs) res
      (recur (next xs)
             (my-cons (make-pair (first xs))
                      res)))))

(defn create-visual 
 ([ps prefix postfix]
   (let [carstr (if (pair? (car ps))
                    (create-visual (car ps))
                    (car ps))
         cdrstr (if (pair? (cdr ps))
                    (create-visual (cdr ps) "" "")
                    (cdr ps))]
     (str prefix carstr " " cdrstr postfix)))
 ([ps] (create-visual ps "(" ")")))
