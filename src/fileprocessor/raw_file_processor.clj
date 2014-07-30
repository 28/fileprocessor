(ns fileprocessor.raw_file_processor
  (:use (clojure [string :only (replace)])
        (clojure.java [io :only (file writer)]))
  (:refer-clojure :exclude [replace])
  (:import [java.io.File]))

(def files-processed (atom 0))

(defn type-of-file
  "Returns the type of the file as extention with dot(.)."
  [^File file]
  (subs (.getName file) (.indexOf (.getName file) ".")))

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

(defn replace-text-with-text
  ""
  [^String directory-path  ^String file-type  ^String old-text ^String new-text]
  (reset! files-processed 0)
  (if
    (or
      (nil? directory-path)
      (not (.isDirectory (file directory-path)))
      (nil? old-text)
      (nil? new-text))
    (throw (IllegalArgumentException. "Illegal directory or text.")))
  (doseq
    [f (rest (file-seq (file directory-path)))]
    (let [fs (slurp f)
          fsr (replace fs old-text new-text)]
      (if (not= fs fsr)
        (with-open [o (writer f :append false)]
        (.write o fsr)
          (swap! files-processed inc)))))
  @files-processed)