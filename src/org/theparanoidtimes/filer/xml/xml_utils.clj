(ns org.theparanoidtimes.filer.xml.xml-utils
  (:require [clojure.data.xml :as dxml]
            [clojure.java.io :as io]))

(defn load-xml
  "Loads a XML file and returns a map representing the XML tree."
  [xml-file]
  (dxml/parse (java.io.FileReader. xml-file)))

(defn xml-map-to-file
  "Spits xml tree map to file."
  [xml-map xml-file-name]
  (with-open
    [fw (io/writer (io/file xml-file-name) :append false :encoding "UTF-8")]
    (dxml/emit xml-map fw)))
