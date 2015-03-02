(ns com.paranoidtimes.fileprocessor.directory-processor
  (:use (clojure.java [io :only (file)])
        fileprocessor.utils))

(defn files-in-directory
  "Returns a lazy seq of all files in given directory. It also can take a variable string
   arguments that represent file types(must be given in .example form) than it returns
   a filtered lazy seq with files of the passed type/types."
  ([directory-path] (rest (file-seq (file directory-path))))
  ([directory-path & file-types]
  (filter (partial (fn [coll f]
               (some #(= (type-of-file f) % ) coll)) file-types)
    (rest (file-seq (file directory-path))))))
