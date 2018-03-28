(ns org.theparanoidtimes.filer.xml.core
  (:require [clojure.java.io :as io]
            [clojure.data.xml :as xml])
  (:import (javax.xml XMLConstants)
           (javax.xml.transform.stream StreamSource)
           (javax.xml.validation SchemaFactory)
           (java.io File FileReader)))


;; XSD schema validation

(defn validate-xml-against-xsd
  "Validates given XML to given XSD schema, returns true if XML is valid,
   false otherwise."
  [^File xml-file ^File xsd-file]
  (let [source-file (StreamSource. xml-file)
        schema-factory (SchemaFactory/newInstance XMLConstants/W3C_XML_SCHEMA_NS_URI)
        schema (.newSchema schema-factory xsd-file)
        validator (.newValidator schema)]
    (try
      (.validate validator source-file)
      true
      (catch Exception _ false))))


;; Util

(defn load-xml
  "Loads a XML file and returns a map representing the XML tree."
  [^File xml-file]
  (xml/parse (FileReader. xml-file)))

(defn xml-map-to-file
  "Spits xml tree map to file."
  [xml-map xml-file-name]
  (with-open
    [fw (io/writer (io/file xml-file-name) :append false :encoding "UTF-8")]
    (xml/emit xml-map fw)))
