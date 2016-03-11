(ns com.paranoidtimes.fileprocessor.xml.xml-processor
  (:require [com.paranoidtimes.fileprocessor.xml.xml-utils :refer :all])
  (:import (javax.xml XMLConstants)
           (javax.xml.transform Source)
           (javax.xml.transform.stream StreamSource)
           (javax.xml.validation Schema SchemaFactory Validator)))

(defn validate-xml-against-xsd
  [^java.io.File xml-file ^java.io.File xsd-file]
  (let [source-file (StreamSource. xml-file)
        validator (. (. (SchemaFactory/newInstance XMLConstants/W3C_XML_SCHEMA_NS_URI) newSchema xsd-file) newValidator)]
    (try
      (. validator validate source-file)
      true
      (catch Exception e false))))

;; Work in progress
#_(defn assoc-to-element
    [xml-map location new-name new-value]
    (map #(update-in % [location]
                     (fn [arg]
                       (into {} (sort compare (merge {new-name new-value} arg)))))
         xml-map))
