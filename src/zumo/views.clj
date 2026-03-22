(ns zumo.views
  (:require [hiccup2.core :as hiccup]))

(def htmx-script "https://unpkg.com/htmx.org@1.9.10")

(defn layout [title & body]
  (str
   (hiccup/html
    {:mode :html}
    (hiccup/raw "<!DOCTYPE html>")
    [:html
     [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
      [:title title]
      [:script {:src htmx-script}]]
     [:body {:hx-boost "true"}
      body]])))

(defn home []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (layout "Zumo"
                 [:main
                  [:h1 "Welcome to Zumo"]
                  [:p "Your Clojure web application is running."]
                  [:div {:hx-get "/health"
                         :hx-trigger "load"
                         :hx-swap "innerHTML"}
                   "Loading..."]])})

(defn not-found []
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body (layout "Not Found"
                 [:h1 "404 - Not Found"]
                 [:p "The page you're looking for doesn't exist."])})
