(ns treasure
  (:gen-class)
  (:require [clojure.string :as str]))

(def mapdata)
(def limitR)
(def limitC)

(defn Find-treasure
  [current data]
  (def currentR (get current 0))
  (def currentC (get current 1))

  (if (= (nth (nth data currentR) currentC) \@)
    (do
      (println "\nYay! Treasure found!!")
      (doseq [item data]
        (println item))
      (System/exit 0)))

  (if (= (nth (nth data currentR) currentC) \-)
    (do
      (def flag false)
      (def newdata (apply str (map-indexed (fn [i c] (if (= currentC i) \+ c)) (get data currentR))))
      (def newmap (assoc data currentR newdata))

      (when (< (+ (get current 0) 1) limitR)
        (Find-treasure (assoc current 0 (+ (get current 0) 1)) newmap)
        (def flag true))
      (when (< (+ (get current 1) 1) limitC)
        (Find-treasure (assoc current 1 (+ (get current 1) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 0) 1) 0)
        (Find-treasure (assoc current 0 (- (get current 0) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 1) 1) 0)
        (Find-treasure (assoc current 1 (- (get current 1) 1)) newmap)
        (def flag true))
      (if flag
        (do
          (def element (apply str (map-indexed (fn [i c] (if (= (get current 1) i) \! c)) (get newmap (get current 0)))))
          (def newmap (assoc newmap (get current 0) element)))))))

(defn check-map-validity
  [mapdata treasure]
  (def rowsize (count mapdata))
  (def colsize (count (nth mapdata 0)))

  (if (>= rowsize 1)
    (do
      (if (not= colsize limitC)
        (do
          (println "\nInvalid Map!")
          (System/exit 0)))

      (if (str/includes? (nth mapdata 0) "@")
        (do
          (def treasurefound true)))

      (if (not= rowsize 1)
        (recur (subvec mapdata 1) treasurefound)))))

(defn check-map-empty
  [mapdata]
  (def size (alength (to-array mapdata)))
  (if (= size 1)
    (do
      (if (str/blank? (nth mapdata 0))
        (do
          (println "Map file is empty!")
          (System/exit 0))))))


(defn start
  []
  (def mapdata (str/split-lines (slurp "map.txt")))
  (println "Treasure Map")
  (doseq [item mapdata]
    (println item))

  (def limitR (count mapdata))
  (def limitC (count (nth mapdata 0)))
  (check-map-empty mapdata)

  (check-map-validity mapdata false)

  (if (not= treasurefound true)
    (do
      (println "Invalid map, no Treasure in the map!")
      (System/exit 0)))

  (Find-treasure [0 0] mapdata)
  (println "\nHard luck! No treasure Found!")
  (doseq [item newmap]
    (println item)))


(start)