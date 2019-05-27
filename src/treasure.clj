(ns treasure
  (:gen-class)
  (:require [clojure.string :as str]))

(def mapdata)
(def i 0)
(def limit)

(defn Find-treasure
  [current data]
  (println limit)
  (def currentI (get current 0))
  (def currentJ (get current 1))
  ;(println current)
  ;(println "data" data)
  ;(println (nth data currentI))
  ;(println (nth (nth data currentI) currentJ))
  ;(println (= (nth (nth data currentI) currentJ) \-))
  (if (= (nth (nth data currentI) currentJ) \-)
    (do
      ;(println "check")
      (def newdata (apply str (map-indexed (fn [i c] (if (= currentJ i) \+ c)) (get data currentI))))
      ;(println newdata)
      (def newmap (assoc data currentI newdata))
      ;(println newmap)
      ;(println current)
      ;(println (assoc current 0 (inc 0)))
      (Find-treasure (assoc current 0 (+ (get current 0) 1)) newmap)
      )
    )
  ;(if (= (nth (nth mapdata currentI) currentJ) \#)
  ;  (do
  ;
  ;    )
  ;  )
  )


(defn start
  []
  (def mapdata (str/split-lines (slurp "map.txt")))
  (def limit (count mapdata))
  ;(println (nth (nth mapdata 1) 0))
  (Find-treasure [0 0] mapdata))

(start)

