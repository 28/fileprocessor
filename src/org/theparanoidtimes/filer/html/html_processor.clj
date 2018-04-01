(ns org.theparanoidtimes.filer.html.html-processor
  (:require [net.cgrand.enlive-html :as h]
            [org.theparanoidtimes.filer.util :refer :all]
            [org.theparanoidtimes.filer.html.html-utils :refer :all]))

(defn get-tags
  "Returns a map of tags for the node specified from the resource."
  [res node]
  (h/select (to-res res) (to-enlive-selector node)))

(defn first-n
  "If value is nil returns all elements of the collection otherwise returns
   that much elements. This is not true for collections with more elements
   than Long/MAX_VALUE and infinite collections."
  [value coll]
  (take
    (if (nil? value)
      Long/MAX_VALUE
      value) coll))

(defn assert-select
  "HTML tag assertion."
  [html node function & {:keys [first n] :or {first nil}}]
  {:pre [(or
           (and (nil? first) (nil? n))
           (and (nil? first) (pos? n))
           (and (nil? n) (pos? first)))]}
  (cond
    (nil? n)
    (map function (first-n first (get-tags html node)))
    :else
    (apply function `(~(nth (get-tags html node) (dec n))))))
