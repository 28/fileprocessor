(ns fileprocessor.directory-processor
  (:use (clojure.java [io :only (file)])
        (clojure [pprint :only (pprint)])
        fileprocessor.utils))

(def testdir "C:\\Users\\Dejan Josifovic\\IdeaProjects\\fileprocessor\\test\\resources")

(defn files-in-directory
  ""
  [directory-path & file-types]
  (filter (partial (fn [coll f]
               (some #(= (type-of-file f) % ) coll)) file-types)
    (rest (file-seq (file directory-path)))))
