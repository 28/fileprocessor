(ns com.paranoidtimes.fileprocessor.html.html-processor
  (:require [net.cgrand.enlive-html :as h]))

(defn- to-enlive-selector
  ""
  [selector]
  (vector (keyword selector)))

(defn- first-n
  ""
  [value coll]
  (take 
   (if 
    (nil? value) 
     java.lang.Integer/MAX_VALUE 
     value) coll))

(defmulti to-res
  (fn [obj] (type obj)))

(defmethod to-res java.lang.String [s]
  (h/html-resource (java.io.StringReader. s)))

(defmethod to-res java.io.File [f]
  (h/html-resource f))

(defn get-tags
  ""
  [res node]
  (-> (h/select res (to-enlive-selector node))))

(defn assert-select
  ""
  [html node function & {:keys [first n] :or {first nil}}]
  {:pre [(or 
          (and (nil? first) (nil? n))
          (and (nil? first) (pos? n))
          (and (nil? n) (pos? first)))]}
  (cond 
    (nil? n)
    (map function (first-n first (get-tags (to-res html) node)))
    :else
    (apply function `(~(nth (get-tags (to-res html) node) (dec n))))))

(defn assert-select-content
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content)))

(defn assert-select-location-content
  ""
  [html node content location]
  (assert-select html node #(= (first (:content %)) content) :n location))
