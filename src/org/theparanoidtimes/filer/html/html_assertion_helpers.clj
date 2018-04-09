(ns org.theparanoidtimes.filer.html.html-assertion-helpers
  (:require [org.theparanoidtimes.filer.html.core :refer :all]))

;; Java interfaces and classes to generate

(gen-interface
  :name "org.theparanoidtimes.filer.NodeProcessingInstruction")

(gen-interface
  :name "org.theparanoidtimes.filer.NodeContentProcessingInstruction"
  :extends [org.theparanoidtimes.filer.NodeProcessingInstruction]
  :methods [[processNodeContent [String] Object]])

(gen-class
  :name "org.theparanoidtimes.filer.HtmlAssertion"
  :prefix "java-"
  :methods [^:static [assertNodeContentIsEqual [Object String String] boolean]
            ^:static [assertNthNodeContentIsEqual [Object String String Integer] boolean]
            ^:static [assertNodeAttributeValue [Object String String String] boolean]
            ^:static [assertOnSpecificNodeContent [Object String Integer org.theparanoidtimes.filer.NodeContentProcessingInstruction] Object]])

;; Clojure

(defn assert-node-content-is-equal
  "Asserts if all selected nodes have the content
   equal to the passed content."
  [html node content]
  (assert-select html node (content=? content)))

(defn assert-nth-node-content-is-equal
  "Asserts if the selected node on the specified location has
   the content equal to the passed content."
  [html node content location]
  (assert-select html node (content=? content) {:n location}))

(defn assert-node-attribute-value
  "Asserts if the selected nodes have a specific attribute with
   the specific value."
  [html node attribute value]
  (assert-select html node (attributes-contain? attribute value)))

;; Java

(defn java-assertNodeContentIsEqual
  "Java interop assert-node-content-is-equal function wrapper."
  [^Object html ^String node ^String content]
  (assert-node-content-is-equal html node content))

(defn java-assertNthNodeContentIsEqual
  "Java interop assert-nth-node-content-is-equal function wrapper."
  [^Object html ^String node ^String content ^Integer location]
  (assert-nth-node-content-is-equal html node content location))

(defn java-assertNodeAttributeValue
  "Java interop assert-node-attribute-value function wrapper."
  [^Object html ^String node ^String attribute ^String value]
  (assert-node-attribute-value html node attribute value))

#_(defn java-assertOnSpecificNodeContent
  ""
  [^Object html ^String node ^Integer location ^org.theparanoidtimes.filer.NodeContentProcessingInstruction instruction]
  (assert-select html node
                 (fn [node] (.processNodeContent instruction (->> node :content first))) :n location))
