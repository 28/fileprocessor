(ns org.theparanoidtimes.filer.html.assertion
  (:require [org.theparanoidtimes.filer.html.core :refer :all]))


;; Java interfaces and classes

(gen-interface
  :name "org.theparanoidtimes.filer.html.assertion.NodeAssertionInstruction")

(gen-interface
  :name "org.theparanoidtimes.filer.html.assertion.NodeContentAssertionInstruction"
  :extends [org.theparanoidtimes.filer.html.assertion.NodeAssertionInstruction]
  :methods [[assertOnContent [String] Boolean]])

(gen-class
  :name "org.theparanoidtimes.filer.html.assertion.HtmlAssertion"
  :prefix "java-"
  :methods [^:static [assertNodeContentIsEqual [Object String String] Boolean]
            ^:static [assertNthNodeContentIsEqual [Object String String Integer] Boolean]
            ^:static [assertNodeAttributeValue [Object String String String] Boolean]
            ^:static [assertLinkNameIsEqual [Object String String] Boolean]
            ^:static [assertLinkTargetIsEqual [Object String String] Boolean]
            ^:static [assertImageSrcIsEqual [Object String String] Boolean]
            ^:static [assertImageSrcAndAltAreEqual [Object String String String] Boolean]
            ^:static [assertImageAsLinkIsEqual [Object String String String] Boolean]
            ^:static [assertOnNodeContent [Object String org.theparanoidtimes.filer.html.assertion.NodeContentAssertionInstruction] Boolean]])


;; Clojure

(defn assert-node-content-is-equal
  "Asserts if all selected nodes have the content equal to the passed content."
  [html node content]
  (assert-select html node (content=? content)))

(defn assert-nth-node-content-is-equal
  "Asserts if the selected node on the specified location has the content equal
   to the passed content."
  [html node content location]
  (assert-select html node (content=? content) {:n location}))

(defn assert-node-attribute-value
  "Asserts if the selected nodes have a specific attribute with the specific
   value."
  [html node attribute value]
  (assert-select html node (attributes-contain? attribute value)))

(defn assert-link-name-is-equal
  "Asserts if the given link node's name is equal to the specified value."
  [html node link-name]
  (assert-select html node (link-name=? link-name)))

(defn assert-link-target-is-equal
  "Asserts if the given link node's target is equal to the specified value."
  [html node href]
  (assert-select html node (link-target=? href)))

(defn assert-img-src-is-equal
  "Asserts if the given image node's source attribute is equal to the specified
   value."
  [html node src]
  (assert-select html node (img-src=? src)))

(defn assert-img-src-and-alt-are-equal
  "Asserts if the given image node's source and alt attributes are equal to the
   specified values."
  [html node src alt]
  (assert-select html node (img-src-alt=? src alt)))

(defn assert-img-as-link-is-equal
  "Asserts if the link node has a target attribute equal to the specified value
   and that the image node that should be in its content has a source attribute
   equal to the specified value."
  [html node src href]
  (assert-select html node (img-as-link=? src href)))


;; Java

(defn java-assertNodeContentIsEqual
  "Java interop assert-node-content-is-equal function wrapper."
  [^Object html ^String node ^String content]
  (assert-node-content-is-equal html (to-selector-vec node) content))

(defn java-assertNthNodeContentIsEqual
  "Java interop assert-nth-node-content-is-equal function wrapper."
  [^Object html ^String node ^String content ^Integer order]
  (assert-nth-node-content-is-equal html (to-selector-vec node) content order))

(defn java-assertNodeAttributeValue
  "Java interop assert-node-attribute-value function wrapper."
  [^Object html ^String node ^String attribute ^String value]
  (assert-node-attribute-value html (to-selector-vec node) attribute value))

(defn java-assertLinkNameIsEqual
  "Java interop assert-link-name-is-equal function wrapper."
  [^Object html ^String node ^String name]
  (assert-link-name-is-equal html (to-selector-vec node) name))

(defn java-assertLinkTargetIsEqual
  "Java interop assert-link-target-is-equal function wrapper."
  [^Object html ^String node ^String href]
  (assert-link-target-is-equal html (to-selector-vec node) href))

(defn java-assertImageSrcIsEqual
  "Java interop assert-img-src-is-equal function wrapper."
  [^Object html ^String node ^String src]
  (assert-img-src-is-equal html (to-selector-vec node) src))

(defn java-assertImageSrcAndAltAreEqual
  "Java interop assert-img-src-and-alt-are-equal function wrapper."
  [^Object html ^String node ^String src ^String alt]
  (assert-img-src-and-alt-are-equal html (to-selector-vec node) src alt))

(defn java-assertImageAsLinkIsEqual
  "Jva interop assert-img-as-link-is-equal function wrapper."
  [^Object html ^String node ^String href ^String src]
  (assert-img-as-link-is-equal html (to-selector-vec node) src href))

(defn java-assertOnNodeContent
  ""
  [^Object html ^String node ^org.theparanoidtimes.filer.html.assertion.NodeContentAssertionInstruction instruction]
  #_(assert-select html node (fn [node] (.assertOnContent instruction (-> node :content filter-content first)))))
