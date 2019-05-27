(ns treasure
  (:gen-class)
  (:require [clojure.string :as str]))

(def mapdata)
(def limitI)
(def limitJ)

(defn Find-treasure
  [current data]
  (def currentI (get current 0))
  (def currentJ (get current 1))

  (if (= (nth (nth data currentI) currentJ) \@)
    (do
      (println "Yay! Treasure found!!")
      (doseq [item data]
        (println item))
      (System/exit 0)))

  (if (= (nth (nth data currentI) currentJ) \-)
    (do
      (def flag false)
      (def newdata (apply str (map-indexed (fn [i c] (if (= currentJ i) \+ c)) (get data currentI))))
      (def newmap (assoc data currentI newdata))

      (when (< (+ (get current 0) 1) limitI)
        (def flag false)
        (Find-treasure (assoc current 0 (+ (get current 0) 1)) newmap)
        (def flag true)
        )
      (when (< (+ (get current 1) 1) limitJ)
        (def flag false)
        (Find-treasure (assoc current 1 (+ (get current 1) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 0) 1) 0)
        (def flag false)
        (Find-treasure (assoc current 0 (- (get current 0) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 1) 1) 0)
        (def flag false)
        (Find-treasure (assoc current 1 (- (get current 1) 1)) newmap)
        (def flag true)
        )
      (if flag
        (do
          (def element (apply str (map-indexed (fn [i c] (if (= (get current 1) i) \! c)) (get newmap (get current 0)))))
          (def newmap (assoc newmap (get current 0) element))
        )
      )
      )
    )
  )


(defn start
  []
  (def mapdata (str/split-lines (slurp "map.txt")))
  (println "Treasure Map")
  (doseq [item mapdata]
    (println item))
  (def limitI (count mapdata))
  (def limitJ (count (nth mapdata 0)))
  (Find-treasure [0 0] mapdata)
  (println "Hard luck! No treasure Found!")
  (doseq [item newmap]
    (println item)))


(start)

