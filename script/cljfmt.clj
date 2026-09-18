(ns cljfmt-script
  "Check or fix Clojure source formatting with cljfmt's default options.

  Usage: bb -f script/cljfmt.clj (check|fix)"
  (:require [cljfmt.core :as cljfmt]
            [clojure.java.io :as io]))

(def ^:private source-dirs ["src" "test"])
(def ^:private file-pattern #"\.clj[csx]?$")

(defn- source-files []
  (->> source-dirs
       (mapcat (fn [dir] (file-seq (io/file dir))))
       (filter (fn [f] (.isFile f)))
       (map str)
       (filter (fn [path] (re-find file-pattern path)))
       sort))

(defn- check [files]
  (let [unformatted (filter (fn [file]
                              (let [original (slurp file)]
                                (not= original (cljfmt/reformat-string original))))
                            files)]
    (if (seq unformatted)
      (do
        (binding [*out* *err*]
          (println "Files not formatted correctly:")
          (doseq [file unformatted] (println " " file))
          (println "Run `bb fmt` to fix."))
        (System/exit 1))
      (println "All source files formatted correctly"))))

(defn- fix [files]
  (doseq [file files
          :let [original (slurp file)
                fixed (cljfmt/reformat-string original)]
          :when (not= original fixed)]
    (spit file fixed)
    (println "Reformatted" file)))

(defn -main [& [command]]
  (let [files (source-files)]
    (case command
      "check" (check files)
      "fix" (fix files)
      (do
        (println "Usage: bb -f script/cljfmt.clj (check|fix)")
        (System/exit 1)))))

(apply -main *command-line-args*)
