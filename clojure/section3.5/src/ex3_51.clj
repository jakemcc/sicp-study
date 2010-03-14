; Exercise 3.51

; Going to rewrite example in Clojure.

(defn show [x]
  (println x)
  x)

; In clojure 'range and 'map are both lazy.
(def x (map show (range 0 10)))

(nth x 5)

(nth x 7)

(comment
  "Any place where user> is input at the repl. Because of difficulties
   in telling what is printed output and what is returned, I will put a p>
   before any output which was printed to the screen instead of being returned"

   "In the book after the first stream-ref (nth here) probably only 0 through 5 were
    printed to the screen before 5 was returned. Then after (stream-ref 7)
    6 and 7 would be printed and 7 returned. This doesn't happen in Clojure as
    streams are 'chunked'.  This means that chunks are evaluated instead of it happening
    one at a time"
user> (def x (map show (range 0 10)))
#'user/x
user> (nth x 5)
p>0
p>1
p>2
p>3
p>4
p>5
p>6
p>7
p>8
p>9
5
user> (nth x 7)
7