(ns org.theparanoidtimes.filer.txt.core
  (:require [clojure.string :as st]
            [org.theparanoidtimes.filer.directory-processor :as dir]
            [clojure.java.io :as io]))


;; Content modification

(defn replace-text-in-file
  ""
  [f old-pattern new-pattern]
  (let [fs (slurp f)
        fr (st/replace fs old-pattern new-pattern)]
    (when (not= fs fr)
      (with-open [out (io/writer f :append false :encoding "UTF-8")]
        (.write out fr)))))

(defn replace-text-in-files
  "Traverses through the given directory and replaces
   the targeted text in files with the given text.
   Function looks only in files that are of the type/s
   from the passed collection. File types must be in form
   like this '.example'. Returns the number of files processed.
   Can also take regular expresion patterns for both old and new
   values (see clojure.string/replace)."
  [directory-path old-pattern new-pattern & file-types]
  (doseq [f (dir/files-in-directory directory-path file-types)]
    (replace-text-in-file f old-pattern new-pattern)))

(defn replace-whole-text-in-file
  ""
  [f new-pattern]
  (replace-text-in-file f #"(?is)^.*$" new-pattern))

(defn replace-whole-text-in-files
  "Traverses through the given directory and
   replaces all the text in files with the
   given text."
  [directory-path new-pattern & file-types]
  (doseq [f (dir/files-in-directory directory-path file-types)]
    (replace-whole-text-in-file f new-pattern)))


;; File generation

(defn generate-files-from-names-list
  "Generates as many files as there are lines in name-list file.
   File names are created by passed name-decorator-fn. This
   function must accept only one arg and return a String representing
   the name of the individual file. Init-text for all files can be provided."
  [name-list-location name-decorator-fn init-text]
  (with-open [reader (io/reader name-list-location)]
    (doseq [name (line-seq reader)]
      (spit (name-decorator-fn name) init-text))))

(defn generate-n-files
  "Generates n files with '<prefix><index>' as name and with
   provided initial text. Index begins at 0."
  [prefix n extension init-text]
  (doseq [i (range 0 n)]
    (spit (str prefix i extension) init-text)))
