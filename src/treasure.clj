(ns treasure
  (:gen-class)
  (:require [clojure.string :as str]))

(def mapdata)
(def i 0)
(def limitI)
(def limitJ)

(defn Find-treasure
  [current data]
  (println limitI)
  (def currentI (get current 0))
  (def currentJ (get current 1))
  (println current)
  (println "data" data)
  ;(println (nth data currentI))
  (println (nth (nth data currentI) currentJ))

  (if (= (nth (nth data currentI) currentJ) \@)
    (do
      (println "Yay! Treasure found!!")
      (doseq [item data]
        (println item))
      (System/exit 0)))

  (println (= (nth (nth data currentI) currentJ) \-))
  (if (= (nth (nth data currentI) currentJ) \-)
    (do
      (println "check")
      (def newdata (apply str (map-indexed (fn [i c] (if (= currentJ i) \+ c)) (get data currentI))))
      ;(println newdata)
      (def newmap (assoc data currentI newdata))
      ;(println newmap)
      (doseq [item newmap]
        (println item))
      ;(println current)
      (when (< (+ (get current 0) 1) limitI)
        (Find-treasure (assoc current 0 (+ (get current 0) 1)) newmap))
      (when (< (+ (get current 1) 1) limitJ)
        (Find-treasure (assoc current 1 (+ (get current 1) 1)) newmap))
      (when (>= (- (get current 0) 1) 0)
        (Find-treasure (assoc current 0 (- (get current 0) 1)) newmap))
      (when (>= (- (get current 1) 1) 0)
        (Find-treasure (assoc current 1 (- (get current 1) 1)) newmap))
      )
    )
  ;(if (= (nth (nth mapdata currentI) currentJ) \#)
  ;  (do
  ;    (println "check1")
  ;
  ;    )
  ;  )
  )


(defn start
  []
  (def mapdata (str/split-lines (slurp "map.txt")))
  (def limitI (count mapdata))
  (def limitJ (count (nth mapdata 0)))
  (Find-treasure [0 0] mapdata))

(start)

