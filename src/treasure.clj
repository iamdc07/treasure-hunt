(ns treasure
  (:gen-class)
  (:require [clojure.string :as str]))

(def mapdata)
(def i 0)
(def limitI)
(def limitJ)

(defn Find-treasure
  [current data]
  ;(println limitI)
  (def currentI (get current 0))
  (def currentJ (get current 1))
  ;(println current)
  ;(println "data" data)
  ;(println (nth data currentI))
  ;(println (nth (nth data currentI) currentJ))

  (if (= (nth (nth data currentI) currentJ) \@)
    (do
      (println "Yay! Treasure found!!")
      (doseq [item data]
        (println item))
      (System/exit 0)))

  ;(if (= currentJ 4)
  ;  (System/exit 0))

  ;(println (= (nth (nth data currentI) currentJ) \-))
  (if (= (nth (nth data currentI) currentJ) \-)
    (do
      ;(println "check")
      (def flag false)
      (def newdata (apply str (map-indexed (fn [i c] (if (= currentJ i) \+ c)) (get data currentI))))
      ;(println newdata)
      (def newmap (assoc data currentI newdata))
      ;(println newmap)
      ;(doseq [item newmap]
      ;  (println item))
      ;(println current)
      (when (< (+ (get current 0) 1) limitI)
        ;(println "Check1")
        (def flag false)
        (Find-treasure (assoc current 0 (+ (get current 0) 1)) newmap)
        (def flag true)
        ;(println "current position " current)
        ;(println "Current map")
        ;(doseq [item data]
        ;  (println item))
        ;(println "New map")
        ;(doseq [item newmap]
        ;  (println item))
        ;(def element (apply str (map-indexed (fn [i c] (if (= (get current 1) i) \! c)) (get data (get current 0)))))
        ;(def newmap (assoc data (get current 0) element))
        ;(println "element" element)
        ;(doseq [item newmap]
        ;  (println item))
        ;(println "element" element)
        )
      (when (< (+ (get current 1) 1) limitJ)
        ;(println "Check2")
        (def flag false)
        (Find-treasure (assoc current 1 (+ (get current 1) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 0) 1) 0)
        ;(println "Check3")
        (def flag false)
        (Find-treasure (assoc current 0 (- (get current 0) 1)) newmap)
        (def flag true))
      (when (>= (- (get current 1) 1) 0)
        ;(println "Check4")
        (def flag false)
        (Find-treasure (assoc current 1 (- (get current 1) 1)) newmap)
        (def flag true)
        )
      (if flag
        (do
          ;(println "flag check")
          ;(doseq [item newmap]
          ;  (println item))
          (def element (apply str (map-indexed (fn [i c] (if (= (get current 1) i) \! c)) (get newmap (get current 0)))))
          (def newmap (assoc newmap (get current 0) element))
          ;(println "post flag check")
          ;(doseq [item newmap]
          ;  (println item))
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

