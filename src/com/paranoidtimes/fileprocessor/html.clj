(ns com.paranoidtimes.fileprocessor.html
  (:require [net.cgrand.enlive-html :as h])
  (:import [java.io.StringReader]))

(defn assertSelect
  ""
  [html node content]
  (let [res (h/html-resource (java.io.StringReader. html))]
    (= content (-> (h/select res (vector (keyword node))) first :content first))))
