(ns org.theparanoidtimes.filer.test.test-utilities
  (:import (java.io File)))

(def test-resources-dir "./test/resources")
(def test-dir "./_test")

(defn file-seq-contains-by-name
  [name col]
  (some #(.contains (.getName %) name) col))

(defn with-test-folder
  [f]
  (.mkdir (File. test-dir))
  (f)
  (doseq [f (reverse (file-seq (File. test-dir)))]
    (.delete f))
  (.delete (File. test-dir)))
