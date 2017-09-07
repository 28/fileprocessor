(ns org.theparanoidtimes.filer.directory-processor
  (:require [clojure.java.io :as io :only [file]]
            [org.theparanoidtimes.filer.utils :as u]))

(defn files-in-directory
  "Returns a lazy seq of all files in given directory. It also
   can take a variable string arguments that represent file
   types (must be given in '.example' form) than it returns
   a filtered lazy seq with files of the passed type/types."
  ([directory-path]
   (filter #(not (.isDirectory %)) (file-seq (io/file directory-path))))
  ([directory-path & file-types]
   (filter (partial (fn [coll f]
                      (some #(and
                              (not (.isDirectory f))
                              (= (u/file-type f) %)) coll)) file-types)
           (file-seq (io/file directory-path)))))

(defn generate-n-directories
  "Generates n directories with names comforting the
   '<prefix><index>' pattern."
  [prefix n]
  (doseq [i (range 0 n)]
    (.mkdirs (io/file (str prefix i)))))
