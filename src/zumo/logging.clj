(ns zumo.logging
  (:require [taoensso.telemere :as telemere]
            [taoensso.truss    :as truss]))

;; defined as macros so telemere can print the callsite's line and column
(defmacro info [message]
  (truss/keep-callsite `(telemere/log! :info ~message)))
(defmacro error [message]
  (truss/keep-callsite `(telemere/log! :error ~message)))
(defmacro warn [message]
  (truss/keep-callsite `(telemere/log! :warn ~message)))
