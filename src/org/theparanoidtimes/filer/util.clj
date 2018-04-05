(ns org.theparanoidtimes.filer.util
  (:import (java.io File)))

(defn file-type
  "Returns a string that represents the type of the file as extension with
   dot(.). For example for file named 'file.txt' file-type returns '.txt'.
   Returns nil for files without the extension."
  [^File f]
  (let [file-name (.getName f)
        index (.lastIndexOf file-name ".")]
    (if (neg? index)
      nil
      (subs file-name index))))
