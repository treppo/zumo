(ns zumo.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]))

(defn load-config []
  (aero/read-config (io/resource "config.edn") {:profile (or (keyword (System/getenv "PROFILE")) :production)}))

(defn port [config] (:port config))
