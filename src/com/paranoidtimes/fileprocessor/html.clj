(ns com.paranoidtimes.fileprocessor.html
  (:require [net.cgrand.enlive-html :as h])
  (:import [java.io.StringReader]))

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
  [html node function & {:keys [first nth] :or {first nil}}]
  (let [res (to-res html)]
    (map function (first-n first (get-tags res node)))))

(defn assert-select-content
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content)))

(defn assert-select-first-content
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content) :first 1))
