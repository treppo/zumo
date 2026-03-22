(ns zumo.handler
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :as parameters]
            [zumo.views :as views]))

(defn routes [db]
  [["/" {:get {:handler (fn [_] (views/home))}}]
   ["/health" {:get {:handler (fn [_] {:status 200
                                       :body "All good!"})}}]])

(defn app [db]
  (ring/ring-handler
   (ring/router
    (routes db)
    {:data {:middleware [parameters/parameters-middleware]}})
   (ring/routes
    (ring/create-default-handler
     {:not-found (constantly (views/not-found))}))))
