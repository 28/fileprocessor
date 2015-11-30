(ns com.paranoidtimes.fileprocessor.html.html-utils
  (:require [net.cgrand.enlive-html :as h]
            [clojure.string :as s]))

(defn to-enlive-selector
  "Transforms the input to a enlive style selector - a vector
   of tag name keywords."
  [selector]
  (into [] (map keyword (s/split selector #"\s"))))

(defmulti to-res
  "Converts the given resource to enlive html-resource."
  (fn [obj] (type obj)))

(defmethod to-res java.lang.String [s]
  "Converts the String resource to enlive html-resource."
  (h/html-resource (java.io.StringReader. s)))

(defmethod to-res :default [r]
  "Default to-res method."
  (h/html-resource r))
