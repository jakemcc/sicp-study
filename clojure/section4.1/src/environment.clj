
(ns environment
  (:use scheme-helpers))

(defn enclosing-environment [env] (rest env))

(defn first-frame [env] (car env))

(def the-empty-environment '())

(defn make-frame [variables values]
  (atom (zipmap variables values)))

(defn frame-variables [frame] (keys @frame))

(defn frame-values [frame] (vals @frame))

(defn add-binding-to-frame! [var val frame]
  (swap! frame assoc var val))

(defn extend-environment [vars vals base-env]
  (if (= (count vars) (count vals))
    (cons (make-frame vars vals) base-env)
    (if (< (count vars) (count vals))
      (Error. (str "Too many arguments supplied " vars vals))
      (Error. (str "Too few arguments supplied " vars vals)))))

(defn lookup-variable-value [variable env]
  (letfn [(env-loop [env]
                    (letfn [(scan [frame]
                                  (if (contains? frame variable)
                                    (get frame variable)
                                    (env-loop (enclosing-environment env))))]
                      (if (= env the-empty-environment)
                        (Error. (str "Unbound variable " variable))
                        (let [frame (first-frame env)]
                          (scan @frame)))))]
    (env-loop env)))

(defn set-variable-value! [variable value env]
  (letfn [(env-loop [env]
                    (letfn [(scan [frame]
                                  (if (contains? @frame variable)
                                    (swap! frame assoc variable value)
                                    (env-loop (enclosing-environment env))))]
                      (if (= env the-empty-environment)
                        (Error. (str "Unbound variable -- SET! " variable))
                        (scan (first-frame env)))))]
    (env-loop env)))

(defn define-variable! [variable value env]
  (let [frame (first-frame env)]
    (if (contains? @frame variable)
      (swap! frame assoc variable value)
      (add-binding-to-frame! variable value frame))))
