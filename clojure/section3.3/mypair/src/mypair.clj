(ns mypair)

(defstruct pair :car :cdr)
(defn pair? [p]
 (= clojure.lang.Atom (type p)))

(defn make-pair 
 ([] (atom (struct pair nil nil)))
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
