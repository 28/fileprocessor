(ns org.theparanoidtimes.filer.dir.core
  (:require [clojure.java.io :as io :only [file]]
            [org.theparanoidtimes.filer.util :as u])
  (:import (java.io File)))


;; Querying

(defn not-dir
  "Asserts if file is not a directory."
  [^File f]
  (not (.isDirectory f)))

(defn file-only-seq
  "Returns a sequence of files of the given directory and it's children that
  are not directories themselves."
  [directory-path]
  (filter not-dir (file-seq (io/file directory-path))))

(defn files-in-directory
  "Returns a lazy seq of all files in given directory. It also
   can take a set that represent file
   types (must be given in '.example' form) than it returns
   a lazy seq with files of the passed type/types."
  ([directory-path]
   (file-only-seq directory-path))
  ([directory-path file-types]
   (filter (partial (fn [coll f]
                      (or
                        (empty? coll)
                        (some #(= (u/file-type f) %) coll))) file-types)
           (file-only-seq directory-path))))


;; Directory generation

(defn generate-n-directories
  "Generates n directories with names comforting the
   '<prefix><index>' pattern where index begins at 0."
  [prefix n]
  (doseq [i (range 0 n)]
    (.mkdirs (io/file (str prefix i)))))
