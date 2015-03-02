(ns com.paranoidtimes.fileprocessor.raw-file-processor
  (:use (clojure [string :only (replace)])
        (clojure.java [io :only (file writer)])
        fileprocessor.directory-processor)
  (:refer-clojure :exclude [replace]))

;; Represents the number of files processed by the last function called.
(def files-processed (atom 0))

(defn replace-text-in-files
  "Traverses through the given directory and replaces the targeted text with the
   given text. Function looks only in files that are of the type/s from the passed
   collection. File types must be in form like this '.example'. Returns the number
   of files processed. Can also take regular expresion patterns for both old and new
   values(see clojure.string/replace)."
  [directory-path old-pattern new-pattern file-types]
  (reset! files-processed 0)
  (doseq
    [f (apply (partial files-in-directory directory-path) file-types)]
    (let [fs (slurp f)
          fsr (replace fs old-pattern new-pattern)]
      (if (not= fs fsr)
        (with-open [o (writer f :append false)]
        (.write o fsr)
          (swap! files-processed inc)))))
  @files-processed)
