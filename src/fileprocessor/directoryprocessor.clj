(ns fileprocessor.directoryprocessor
  (:use (clojure.java [io :only (file)])
        (clojure [pprint :only (pprint)])
        fileprocessor.processor))

(defn print-files-in-directory
  ""
  [directory-path file-type]
  (filter #(= (type-of-file %) file-type)
    (file-seq (file directory-path))))
