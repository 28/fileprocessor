(ns fileprocessor.directory_processor
  (:use (clojure.java [io :only (file)])
        (clojure [pprint :only (pprint)])
        fileprocessor.processor))

(defn print-files-in-directory
  "File types must be a set for now"
  [directory-path file-types]
  (filter  #(contains? file-types (type-of-file %))
    (rest (file-seq (file directory-path)))))
