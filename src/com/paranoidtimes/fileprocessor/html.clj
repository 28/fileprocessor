(ns com.paranoidtimes.fileprocessor.html
  (:require [net.cgrand.enlive-html :as h])
  (:import [java.io.StringReader]))

(defn- to-enlive-selector
  ""
  [selector]
  (vector (keyword selector)))

(defn assert-select
  ""
  [html node function & flags]
  (let [res (h/html-resource (java.io.StringReader. html))
        flags (set flags)]
    (map function (-> (h/select res (to-enlive-selector node))))))

(defn assert-select-content
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content)))

(defn assert-select-first-content
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content) :first))
