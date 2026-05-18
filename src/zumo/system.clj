(ns zumo.system
  (:require
   [integrant.core :as ig]
   [zumo.config :as config]
   [zumo.logging :as logging]
   [zumo.web :as web]))

(def conf (zumo.config/load-config))

(defn components []
  {:zumo/server {:port (config/port conf)}})

(defmethod ig/init-key :zumo/server [_ {:keys [port]}]
  (logging/info (str "Starting server on port " port))
  (web/server port))

(defmethod ig/halt-key! :zumo/server [_ server]
  (logging/info "Shutting down server gracefully")
  ;; Graceful shutdown (wait <=1000 msecs for existing requests to complete):
  (server :timeout 1000))
