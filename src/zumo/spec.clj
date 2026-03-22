(ns zumo.spec
  (:require [clojure.alpha.spec :as s]
            [clojure.alpha.spec.gen :as gen]))

;; Example specs using spec-alpha2
;; Define your application specs here

(s/def ::id pos-int?)
(s/def ::name (s/and string? #(< 0 (count %) 256)))
(s/def ::email (s/and string? #(re-matches #".+@.+\..+" %)))
