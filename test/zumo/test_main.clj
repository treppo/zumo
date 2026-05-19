(ns zumo.test-main
  (:require [cognitect.test-runner.api]
            [clojure+.test]))

(defn -main [& args]
  (clojure+.test/install!)
  (cognitect.test-runner.api/test args))
