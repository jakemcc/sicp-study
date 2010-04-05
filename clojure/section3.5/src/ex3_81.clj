; Exercise 3.81

(ns ex3-81)

(defn rand-update [x]
  (let [a 5.2 b 3.1 m 1.5]
    (mod (+ (* a x)
            b)
         m)))

(defn gen-rand [cmds]
  ((fn me [next-value s]
     (lazy-seq
      (when-let [c (seq s)]
        (let [cmd (first c)]
          (cond (= :generate cmd)
                (cons next-value
                      (me (rand-update next-value)
                          (rest c)))
                (= :reset cmd)
                (cons (second c)
                      (me (rand-update (second c))
                          (rest (rest c))))
                :else '())))))
   0.5 cmds))
