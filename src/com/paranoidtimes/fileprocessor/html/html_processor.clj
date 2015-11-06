(ns com.paranoidtimes.fileprocessor.html.html-processor
  (:require [net.cgrand.enlive-html :as h]
            [com.paranoidtimes.fileprocessor.utils :refer :all]
            [com.paranoidtimes.fileprocessor.html.html-utils :refer :all]))

(defn get-tags
  ""
  [res node]
  (h/select (to-res res) (to-enlive-selector node)))

(defn assert-select
  ""
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
