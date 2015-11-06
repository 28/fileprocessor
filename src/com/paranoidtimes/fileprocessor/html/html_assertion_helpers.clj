(ns com.paranoidtimes.fileprocessor.html.html-assertion-helpers
  (:require [com.paranoidtimes.fileprocessor.html.html-processor :refer :all]))

(defn assert-node-content-is-equal
  "Asserts if all selected nodes have the content
   equal to the passed content."
  [html node content]
  (assert-select html node #(= (first (:content %)) content)))

(defn assert-nth-node-content-is-equal
  "Asserts if the selected node on the specified location has
   the content equal to the passed content."
  [html node content location]
  (assert-select html node #(= (first (:content %)) content) :n location))

(defn assert-node-attribute-value
  "Asserts if the selected nodes have a specific attribute with
   the specific value."
  [html node attribute value]
  (let [kwd (keyword attribute)]
    (assert-select html node #(= (->> % :attrs kwd) value))))
