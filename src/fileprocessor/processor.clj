(ns fileprocessor.processor
  (:use (clojure [string :only (replace)])
        (clojure.java [io :only (file writer)]))
  (:refer-clojure :exclude [replace])
  (:import [java.io.File]))

(def files-processed (atom 0))

(defn substring?
  "Returns true if string contains the given substring."
  [^String string ^String substring]
  (not= (.indexOf string substring) -1))

(defn get-file-type
  "Returns the type of the file as extention."
  [file]
  (let [i (.indexOf (.getName file) ".")]
    (if
        (> i -1)
      (subs (.getName file) i))))

(defn replace-text-in-files
  ""
  [^String directory-path  ^String file-type ^String old-text ^String new-text]
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
    (let [fs (slurp f)]
      (if
          (substring? fs old-text)
        (let [rfs (replace fs old-text new-text)]
         (with-open [o (writer f :append false)]
           (.write o rfs))
         (swap! files-processed inc)))))
  @files-processed)

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
