(ns com.paranoidtimes.fileprocessor.html.html-utils
  (:require [net.cgrand.enlive-html :as h]))

(defn to-enlive-selector
  ""
  [selector]
  (vector (keyword selector)))

(defmulti to-res
  (fn [obj] (type obj)))

(defmethod to-res java.lang.String [s]
  (h/html-resource (java.io.StringReader. s)))

(defmethod to-res java.io.File [f]
  (h/html-resource f))
