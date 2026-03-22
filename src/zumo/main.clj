(ns zumo.main
  (:require [integrant.core :as ig]
            [zumo.system :as system])
  (:gen-class))

(defn -main [& _args]
  (-> (system/components)
      (ig/init)))
