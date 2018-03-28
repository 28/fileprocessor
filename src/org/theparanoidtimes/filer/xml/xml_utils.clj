(ns org.theparanoidtimes.filer.xml.xml-utils
  (:require [clojure.data.xml :as xml]
            [clojure.java.io :as io])
  (:import (java.io File FileReader)))

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
