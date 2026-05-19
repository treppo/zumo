(ns zumo.e2e.health-test
  (:require [clojure.test :refer [deftest is]]
            [org.httpkit.client :as http]
            [zumo.main :as main])
  (:import [java.net ConnectException]))

(def ^:private server-port 56789)
(def ^:private server-url (str "http://localhost:" server-port))
(def ^:private health-endpoint
  (str server-url "/health"))

(defn- http-get [url]
  @(http/get url {:as :text :timeout 100}))

(defn- server-ready? []
  (try
    (let [resp (http-get health-endpoint)]
      (= 200 (:status resp)))
    (catch ConnectException _
      false)
    (catch Exception _
      false)))

(defn- wait-for-server []
  (let [start (System/currentTimeMillis)
        timeout-ms 5000]
    (loop []
      (if (server-ready?)
        true
        (if (< (- (System/currentTimeMillis) start) timeout-ms)
          (do (Thread/sleep 10)
              (recur))
          (throw (RuntimeException. (str "Server did not start within " timeout-ms "ms"))))))))

(deftest application-starts-successfully
  (let [system (main/-main)]
    (try
      (wait-for-server)
      (let [response (http-get health-endpoint)]
        (is (= 200 (:status response))))
      (finally
        (main/halt system)))))
