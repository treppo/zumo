(ns zumo.config
  (:require [aero.core :as aero]))

(defn load-config []
  (aero/read-config "config.edn" {:profile (or (keyword (System/getenv "PROFILE")) :production)}))

(defn port [config] (:port config))
