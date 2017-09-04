(ns org.theparanoidtimes.filer.directory-processor
  (:use (clojure.java [io :only (file)])
        org.theparanoidtimes.filer.utils))

(defn files-in-directory
  "Returns a lazy seq of all files in given directory. It also can take a variable string
   arguments that represent file types(must be given in .example form) than it returns
   a filtered lazy seq with files of the passed type/types."
  ([directory-path] (rest (file-seq (file directory-path))))
  ([directory-path & file-types]
   (filter (partial (fn [coll f]
                      (some #(= (file-type f) %) coll)) file-types)
           (rest (file-seq (file directory-path))))))

(defn generate-n-directories
  [n prefix]
  (for [i (range 0 n)]
    (.mkdir (file (str prefix i)))))
