(ns fileprocessor.processor
  (:use (clojure [string :only (replace)])
        (clojure.java [io :only (file writer)]))
  (:refer-clojure :exclude [replace])
  (:import [java.io.File]))

(def files-processed (atom 0))

(defn type-of-file
  "Returns the type of the file as extention."
  [file]
  (let [i (.indexOf (.getName file) ".")]
    (if
        (> i -1)
      (subs (.getName file) i))))

(defn replace-text-with-regex
  ""
  [^String directory-path  ^String file-type  ^String regex ^String new-regex]
  (reset! files-processed 0)
  (if
      (or
       (nil? directory-path)
       (not (.isDirectory (file directory-path)))
       (nil? regex)
       (nil? new-regex))
    (throw (IllegalArgumentException. "Illegal directory or text.")))
	(doseq
      [f (rest (file-seq (file directory-path)))]
      (let [fs (slurp f)
            fsr (replace fs (re-pattern regex) new-regex)]
        (with-open [o (writer f :append false)]
          (.write o fsr))
        (swap! files-processed inc)))
  @files-processed)
