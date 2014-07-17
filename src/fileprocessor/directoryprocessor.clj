(ns fileprocessor.directoryprocessor
  (:use (clojure.java [io :only (file)])
        (clojure [pprint :only (pprint)])
        fileprocessor.processor))

(defn print-files-in-directory
  ""
  [directory-path file-type]
  (doseq [f (rest (file-seq (file directory-path)))]
    (if
        (= (get-file-type f) file-type)
      f)))
