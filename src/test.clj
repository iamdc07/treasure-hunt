(ns test)

(def myvar ["------------@"])
(println (clojure.string/includes? "------------@" "@")) ; true
;(println (nth myvar 0))
(if (clojure.string/includes? (nth myvar 0) "@")
  (println "Inside If"))