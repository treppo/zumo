(ns build
  (:require [clojure.tools.build.api :as b]))

(def project-name 'zumo)
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s.jar" (name project-name)))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uberjar [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources"], :target-dir class-dir})
  (b/compile-clj {:basis basis, :src-dirs ["src"], :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'zumo.main}))

(defn native-image [_]
  (println "› Step 1: Build uberjar ...")
  (uberjar nil)
  (println "\n› Step 2: Build native image ...")
  (let [{:keys [exit out err]} (b/process {:command-args ["native-image"
                                                          "-jar" uber-file
                                                          "-o target/zumo"
                                                          "--no-fallback"
                                                          "--initialize-at-build-time"
                                                          "--enable-url-protocols=http,https"
                                                          "-H:+ReportExceptionStackTraces"
                                                          "-march=native"
                                                          "--verbose"]})]
    (println out)
    (when (seq err) (println err))
    (println (if (zero? exit)
               "\n✓ Native image created at target/zumo"
               (str "\n✗ Native image build failed with exit code " exit)))
    (System/exit exit)))
