(ns com.paranoidtimes.fileprocessor.html.html-utils
  (:require [net.cgrand.enlive-html :as h]
            [clojure.string :as s])
  (:import (java.io StringReader)))

(defn to-enlive-selector
  "Transforms the input to a enlive style selector - a vector
   of tag name keywords."
  [selector]
  (into [] (map keyword (s/split selector #"\s"))))

(defmulti to-res
          "Converts the given resource to enlive html-resource."
          (fn [obj] (type obj)))

(defmethod to-res String [s]
  "Converts the String resource to enlive html-resource."
  (h/html-resource (StringReader. s)))

(defmethod to-res :default [r]
  "Default to-res method."
  (h/html-resource r))
