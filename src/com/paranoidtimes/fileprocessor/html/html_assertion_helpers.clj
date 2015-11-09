(ns com.paranoidtimes.fileprocessor.html.html-assertion-helpers
  (:require [com.paranoidtimes.fileprocessor.html.html-processor :refer :all]))

(gen-class
 :name "com.paranoidtimes.fileprocessor.HtmlAssertion"
 :prefix "java-"
 :methods [^:static [assertNodeContentIsEqual [Object String String] boolean]
           ^:static [assertNthNodeContentIsEqual [Object String String Integer] boolean]
           ^:static [assertNodeAttributeValue [Object String String String] boolean]])

; Clojure

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

; Java

(defn java-assertNodeContentIsEqual
  [^Object html ^String node ^String content]
  (assert-node-content-is-equal html node content))

(defn java-assertNthNodeContentIsEqual
  [^Object html ^String node ^String content ^Integer location]
  (assert-nth-node-content-is-equal html node content location))

(defn java-assertNodeAttributeValue
  [^Object html ^String node ^String attribute ^String value]
  (assert-node-attribute-value html node attribute value))
