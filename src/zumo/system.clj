(ns zumo.system
  (:require [integrant.core :as ig]
            [zumo.web :as web]
            [zumo.config :as config]))

(def conf (zumo.config/load-config))

(defn components []
  {:zumo/server {:port (config/port conf)}})

(defmethod ig/init-key :zumo/server [_ {:keys [port]}]
  (web/server port))

(defmethod ig/halt-key! :zumo/server [_ server]
  ;; Graceful shutdown (wait <=1000 msecs for existing requests to complete):
  (server :timeout 1000))
