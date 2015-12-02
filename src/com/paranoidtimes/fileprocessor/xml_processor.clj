(ns com.paranoidtimes.fileprocessor.xml-processor
  (:require [clojure.data.xml :as dxml])
  (:import (javax.xml XMLConstants)
           (javax.xml.transform Source)
           (javax.xml.transform.stream StreamSource)
           (javax.xml.validation Schema SchemaFactory Validator)))

(defn validate-xml-against-xsd
  [xml-file xsd-file]
  (let [source-file (StreamSource. xml-file)
        validator (. (. (SchemaFactory/newInstance XMLConstants/W3C_XML_SCHEMA_NS_URI) newSchema xsd-file) newValidator)]
    (try
      (. validator validate source-file)
      true
      (catch Exception e false))))

(defn load-xml
  [xml-file]
  (dxml/parse (java.io.FileReader. xml-file)))

(defn xml-map-to-file
  [xml-map xml-file-name]
  (with-open 
   [fw (java.io.FileWriter. xml-file-name)]
    (dxml/emit xml-map fw)))
