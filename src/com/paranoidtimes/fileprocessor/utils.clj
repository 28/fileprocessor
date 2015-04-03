(ns com.paranoidtimes.fileprocessor.utils
  (:import (java.io File)))

(defn file-type
  "Returns a string that represents the type of the file
   as extention with dot (Example: .txt)."
  [^File f]
  (let [file-name (.getName f)
        index (.lastIndexOf file-name ".")]
    (if
      (neg? index)
      nil
      (subs file-name index))))
