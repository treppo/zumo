(ns zumo.main
  (:require [integrant.core :as integrant]
            [zumo.system :as system])
  (:gen-class))

(defn -main [& _args]
  (-> (system/components)
      (integrant/init)))

(defn halt [system]
  (integrant/halt! system))
