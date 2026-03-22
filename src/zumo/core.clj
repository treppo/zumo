(ns zumo.core
  (:require [integrant.core :as ig]
            [zumo.system :as system])
  (:gen-class))

(defn -main [& _args]
  (println "Starting Zumo...")
  (-> (system/system)
      (ig/init)))
