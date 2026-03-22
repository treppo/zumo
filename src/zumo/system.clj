(ns zumo.system
  (:require [integrant.core :as ig]
            [org.httpkit.server :as httpkit-server]
            [zumo.handler :as handler]
            [zumo.config :as config]
            [zumo.db :as db]))

(def c (config/config))

(defn system []
  {:zumo/db {:jdbc-url (config/database-url c)}

   :zumo/handler {:db (ig/ref :zumo/db)}

   :zumo/server {:handler (ig/ref :zumo/handler)
                 :port (config/port c)}})

(defmethod ig/init-key :zumo/db [_ {:keys [jdbc-url]}]
  (println "Connecting to database...")
  (db/datasource jdbc-url))

(defmethod ig/halt-key! :zumo/db [_ datasource]
  (println "Closing database connection...")
  (.close datasource))

(defmethod ig/init-key :zumo/handler [_ {:keys [db]}]
  (handler/app db))

(defmethod ig/init-key :zumo/server [_ {:keys [handler port]}]
  (println (str "Starting server on port " port "..."))
  (httpkit-server/run-server handler {:port port}))

(defmethod ig/halt-key! :zumo/server [_ server]
  (println "Stopping server gracefully...")
  ;; Graceful shutdown (wait <=1000 msecs for existing requests to complete):
  (server :timeout 1000))
