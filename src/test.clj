(ns test
  (:require [clojure.string :as str]))

(def newvar ["this" "is" "a" "car"])
(def newarr (to-array newvar))
;(println (aget newarr 0))
(println newvar)

(def hello (to-array "Hello World!"))

(aset hello 1 \b)
(println (aget hello 1))

;(def myvar (list 1 2 3))
;(println (count (to-array myvar)))
;(println (count (to-array newvar)))
;
;(println (apply str ()))

(println (apply str (map-indexed (fn [i c] (if (= 3 i) \X c)) "foobar")))

(def abc [1 2 3])
(println (assoc abc 0 (inc 1)))