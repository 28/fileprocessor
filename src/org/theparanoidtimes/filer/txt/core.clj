(ns org.theparanoidtimes.filer.txt.core
  (:require [clojure.string :as st]
            [org.theparanoidtimes.filer.dir.core :as dir]
            [clojure.java.io :as io]))


;; Content modification

(defn replace-text-in-file
  "Replaces the 'old-pattern' in file 'f' with the 'new pattern'. Does not
   touch the file if content is unchanged. Writes in the with UTF-8 encoding.
   Patterns can be regexps. See clojure.string/replace for details."
  [f old-pattern new-pattern]
  (let [fs (slurp f)
        fr (st/replace fs old-pattern new-pattern)]
    (when (not= fs fr)
      (with-open [out (io/writer f :append false :encoding "UTF-8")]
        (.write out fr)))))

(defn replace-text-in-files
  "Traverses through the given directory and replaces the 'old-pattern' in files
   with the 'new-pattern'. If passed a set of file types only those files will
   be considered. File types must be in the following format: #{'.txt' 'clj'}.
   See org.theparanoidtimes.filer.txt.core/replace-text-in-file for details."
  ([directory-path old-pattern new-pattern]
   (replace-text-in-files directory-path old-pattern new-pattern nil))
  ([directory-path old-pattern new-pattern file-types]
   (doseq [f (dir/files-in-directory directory-path file-types)]
     (replace-text-in-file f old-pattern new-pattern))))

(defn replace-whole-text-in-file
  "Replaces the whole content of the given file 'f' with the 'new-pattern'."
  [f new-pattern]
  (replace-text-in-file f #"(?is)^.*$" new-pattern))

(defn replace-whole-text-in-files
  "Traverses through the given directory and replaces all the text in files with
   the given text. If passed a set of file types only those files will be
   considered. File types must be in the following format: #{'.txt' 'clj'}.
   See org.theparanoidtimes.filer.txt.core/replace-text-in-file for details."
  ([directory-path new-pattern]
   (replace-whole-text-in-files directory-path new-pattern nil))
  ([directory-path new-pattern file-types]
   (doseq [f (dir/files-in-directory directory-path file-types)]
     (replace-whole-text-in-file f new-pattern))))


;; File generation

(defn generate-files-from-names-list
  "Generates as many files as there are lines in name list file. File names are
   created by passed 'name-decorator-fn'. This function must accept only one arg
   and must return a String representing the name of the individual file.
   'init-text' will be written in every file."
  [name-list-location name-decorator-fn init-text]
  (with-open [reader (io/reader name-list-location)]
    (doseq [name (line-seq reader)]
      (spit (name-decorator-fn name) init-text))))

(defn generate-n-files
  "Generates n files with '<prefix><index><extension>' as name and with provided
  initial text. Index begins at 0."
  [prefix n extension init-text]
  (doseq [i (range 0 n)]
    (spit (str prefix i extension) init-text)))
