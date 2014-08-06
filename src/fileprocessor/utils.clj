(ns fileprocessor.utils)

(defn type-of-file
  "Returns the type of the file as extention with dot(Example: .txt)."
  [^File file]
  (let [file-name (.getName file)]
    (subs file-name (.indexOf file-name "."))))
