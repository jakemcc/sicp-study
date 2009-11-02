; Exercise 2.6

(def zero (fn [f] (fn [x] x)))

(defn add-1 [n]
 (fn [f] (fn [x] (f ((n f) x)))))

; (add-1 zero)
; (add-1 (fn [f] (fn [x] x)))
; (fn [f] (fn [x] (f (((fn [f] (fn [x] x))) f) x))))
; (fn [f] (fn [x] (f x))    == one

(def one (fn [f] (fn [x] (f x))))

; (add-1 one)
; (add-1 (fn [f] (fn [x] (f x))))
; (fn [f] (fn [x] (f ((fn [f] (fn [x] (f x))) f) x)))
; (fn [f] (fn [x] (f ((fn [x] (f x))) x)))
; (fn [f] (fn [x] (f (f x))))    == two

(def two (fn [f] (fn [x] (f (f x)))))

(defn- compose [f g]
 (fn [x] (f (g x))))

; Modeled after add-1.  add-1 passes f
; as an argument to number passed to it.
; Compose the two numbers to get them to
; be more nexted f calls
(defn add-church [a b]
 (fn [f]
  (compose (a f) (b f))))

((one inc) 0)
((two inc) 0)
(((add-church two two) inc) 0)


; http://briancarper.net/blog/church-numerals-in-clojure
; Good blog post.  Brian's unchurch function was useful
; in wrapping mind around this
