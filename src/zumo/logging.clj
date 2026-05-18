(ns zumo.logging
  (:require [taoensso.telemere :as telemere]))

(defn info [message] (telemere/log! :info message))
(defn error [message] (telemere/log! :error message))
(defn warn [message] (telemere/log! :warn message))
