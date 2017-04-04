(ns com.paranoidtimes.fileprocessor.utils
  (:import (java.io File)))

(defn file-type
  "Returns a string that represents the type of the file
   as extention with dot (example: from test.txt returns
   .txt)."
  [^File f]
  (let [file-name (.getName f)
        index (.lastIndexOf file-name ".")]
    (if
      (neg? index)
      nil
      (subs file-name index))))

(defn first-n
  "If value is nil returns all elements of the collection otherwise returns
   that much elements. This is not true for collections with more elements
   than Long/MAX_VALUE and infinite collections."
  [value coll]
  (take
    (if
      (nil? value)
      Long/MAX_VALUE
      value) coll))
