(ns fileprocessor.utils)

(defn substring?
  "Returns true if string contains the given substring."
  [^String string ^String substring]
  (not= (.indexOf string substring) -1))
