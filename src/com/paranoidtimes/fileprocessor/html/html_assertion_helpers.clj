(ns com.paranoidtimes.fileprocessor.html.html-assertion-helpers
  (:require [com.paranoidtimes.fileprocessor.html.html :refer :all]))

(defn assert-node-content-is-equal
  ""
  [html node content]
  (assert-select html node #(= (first (:content %)) content)))

(defn assert-nth-node-content-is-equal
  ""
  [html node content location]
  (assert-select html node #(= (first (:content %)) content) :n location))
