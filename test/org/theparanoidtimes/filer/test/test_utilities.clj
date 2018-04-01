(ns org.theparanoidtimes.filer.test.test-utilities
  (:require [clojure.java.io :as io])
  (:import (java.io File)))

(def ^String test-resources-dir-name "./test/resources")
(def ^String test-dir-name "./_test")

(defn test-resources-dir
  []
  (io/file test-resources-dir-name))

(defn test-dir
  []
  (io/file test-dir-name))

(defn fname
  [file-name]
  (str test-dir-name "/" file-name))

(defn f
  [file-name]
  (io/file (fname file-name)))

(defn rname
  [file-name]
  (str test-resources-dir-name "/" file-name))

(defn r
  [file-name]
  (io/file (rname file-name)))

(defn file-seq-contains-file
  [name seq]
  (some #(.contains (.getName %) name) (filter #(not (.isDirectory %)) seq)))

(defn file-seq-contains-files
  [names seq]
  (every? names (map #(.getName %) (filter #(not (.isDirectory %)) seq))))

(defn with-test-folder
  [f]
  (let [d (File. test-dir-name)]
    (.mkdir d)
    (f)
    (doseq [f (reverse (file-seq d))]
      (.delete f))
    (.delete d)))
