(ns org.theparanoidtimes.filer.xml.xml-processor
  (:require [org.theparanoidtimes.filer.xml.xml-utils :refer :all])
  (:import (javax.xml XMLConstants)
           (javax.xml.transform Source)
           (javax.xml.transform.stream StreamSource)
           (javax.xml.validation Schema SchemaFactory Validator)))

(defn validate-xml-against-xsd
  "Validates given XML to given XSD schema, returns true if XML is valid,
   false otherwise."
  [^java.io.File xml-file ^java.io.File xsd-file]
  (let [source-file (StreamSource. xml-file)
        schema-factory (SchemaFactory/newInstance XMLConstants/W3C_XML_SCHEMA_NS_URI)
        schema (.newSchema schema-factory xsd-file)
        validator (.newValidator schema)]
    (try
      (.validate validator source-file)
      true
      (catch Exception e false))))
