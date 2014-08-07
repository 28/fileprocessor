(ns fileprocessor.utils
  (:import (java.io File)))

(defn type-of-file
  "Returns a string that represents the type of the file
   as extention with dot(Example: .txt)."
  [^File f]
  (let [file-name (.getName f)]
    (subs file-name (.indexOf file-name "."))))
