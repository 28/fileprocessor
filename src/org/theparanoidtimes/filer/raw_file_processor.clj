(ns org.theparanoidtimes.filer.raw-file-processor
  (:use (clojure.java [io :only (file writer)]))
  (:require [clojure.string :as st]
            [org.theparanoidtimes.filer.directory-processor :refer :all]))

;; Represents the number of files processed by the last replace-text-in-files call.
(def files-processed (atom 0))

(defn replace-text-in-files
  "Traverses through the given directory and replaces the targeted text in files with
   the given text. Function looks only in files that are of the type/s from the passed
   collection. File types must be in form like this '.example'. Returns the number
   of files processed. Can also take regular expresion patterns for both old and new
   values (see clojure.string/replace)."
  [directory-path old-pattern new-pattern file-types]
  (reset! files-processed 0)
  (doseq
    [f (apply (partial files-in-directory directory-path) file-types)]
    (let [fs (slurp f)
          fsr (st/replace fs old-pattern new-pattern)]
      (if (not= fs fsr)
        (with-open [o (writer f :append false)]
          (.write o fsr)
          (swap! files-processed inc)))))
  @files-processed)

(defn replace-whole-text-in-files
  "Traverses through the given directory and replaces all the text in files with the
   given text."
  [directory-path new-pattern file-types]
  (replace-text-in-files directory-path #"(?is)^.*$" new-pattern file-types))

(defn generate-files-from-names-list
  "Generates as many files as there are lines in name-list file. Names are
   decorated by passed decorator-fn. This function must accept only one arg and
   return a String representing the name of the individual file. Init-text for all
   files can be provided."
  [name-list decorator-fn init-text]
  (with-open [reader (clojure.java.io/reader name-list)]
    (let [lines (line-seq reader)]
      (doall (map #(spit (decorator-fn %) init-text) lines)))))

(defn generate-n-files
  "Generates n files with prefix + index as name and with passed
   init text."
  [n prefix init-text]
  (for [i (range 0 n)]
    (spit (str prefix i) init-text)))
