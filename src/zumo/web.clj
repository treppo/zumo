(ns zumo.web
  (:require
   [org.httpkit.server :as httpkit-server]
   [reitit.ring :as ring]
   [reitit.ring.middleware.parameters :as parameters]
   [zumo.views :as views]))

(defn- routes []
  [["/" {:get {:handler (fn [_] (views/home))}}]
   ["/health" {:get {:handler (fn [_] {:status 200
                                       :body "All good!"})}}]])

(defn- router []
  (ring/router
   (routes)
   {:data {:middleware [parameters/parameters-middleware]}}))

(defn- handler []
  (ring/ring-handler
   (router)
   (ring/routes
    (ring/create-default-handler
     {:not-found (constantly (views/not-found))}))))

(defn server [port]
  (httpkit-server/run-server (handler) {:port port}))
