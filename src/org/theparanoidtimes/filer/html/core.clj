(ns org.theparanoidtimes.filer.html.core
  (:require [net.cgrand.enlive-html :as h]
            [clojure.string :as s])
  (:import (java.io StringReader)))


;; Util

(defmulti to-res
          "Converts the given resource to enlive html-resource (map of nodes)."
          (fn [obj] (type obj)))

(defmethod to-res String [s]
  "Converts the String resource to enlive html-resource."
  (h/html-resource (StringReader. s)))

(defmethod to-res :default [r]
  "Default to-res method, just calls enlive html-resource."
  (h/html-resource r))

(defn to-selector-vec
  "Transforms the input to a enlive style selector - a vector of tag name
   keywords."
  [selector]
  (into [] (map keyword (s/split selector #"\s"))))

(defn filter-content
  "Takes a collection and filters out whitespace strings."
  [content-coll]
  (remove #(and (string? %)
                (re-matches #"\s+" %)) content-coll))


;; Assertion

(defn assert-select
  "Takes three arguments. First is HTML represented as a string or a file or it
   can be a map of nodes. The second argument is a node path. Third argument is
   an assertion function that will be mapped to a collection of nodes returned
   by the node path applied on the HTML. Assertion function should be a
   predicate. The function returns true when all assertions on all nodes are
   true and false otherwise. See also - to-res, h/select."
  ([html node-path assertion-fn]
   (assert-select html node-path assertion-fn nil))
  ([html node-path assertion-fn opts]
   (->> html
        to-res
        (#(h/select % node-path))
        (#(if-let [order (:n opts)]
            (list (nth % order))
            %))
        (map assertion-fn)
        (#(and
            (not (empty? %))
            (every? true? %))))))

(defn content=?
  "Returns a function that takes a node. Asserts if the node content is equal
   to the passed content. The content being asserted is filtered. See also -
   filter-content."
  [c]
  (fn [node]
    (-> node
        :content
        filter-content
        first
        (= c))))

(defn attributes-contain?
  "Returns a function that takes a node. Asserts if the node has passed
   key/value pair in its attribute map."
  [key value]
  (fn [node]
    (-> node
        (get :attrs)
        (get key)
        (= value))))

(defn link-name=?
  "A util function that is same as content=? but used on asserting link name
   which is basically the link node's content. See also - content=?."
  [name]
  (content=? name))

(defn link-target=?
  "Returns a function that takes a node. Asserts if the :href attribute of a
   link node is equal with the passed value."
  [href]
  (fn [node]
    (-> node
        (get :attrs)
        (get :href)
        (= href))))

(defn img-src=?
  "Returns a function that takes a node. Asserts if the :src attribute of an
   image node is equal to the passed value."
  [src]
  (fn [node]
    (-> node
        (get :attrs)
        (get :src)
        (= src))))

(defn img-src-alt=?
  "Returns a function that takes a node. Asserts if the :src and :alt attributes
   of an image node are equal to the passed values."
  [src alt]
  (fn [node]
    (-> node
        (get :attrs)
        (#(and (= (get % :src) src)
               (= (get % :alt) alt))))))

(defn img-as-link=?
  "Returns a function that takes a node. The node should be a link node with
   a image node as its content. The functions asserts if the :src attribute of
   the image and :href attribute of the link are equal to the passed values."
  [src href]
  (fn [node]
    (let [h (-> node
                (get :attrs)
                (get :href))
          s (-> node
                (get :content)
                filter-content
                first
                (get :attrs)
                (get :src))]
      (and (= s src)
           (= h href)))))
