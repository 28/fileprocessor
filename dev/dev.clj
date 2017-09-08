(ns dev
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer (pprint)]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [org.theparanoidtimes.filer.utils :refer :all]
            [org.theparanoidtimes.filer.directory-processor :refer :all]
            [org.theparanoidtimes.filer.raw-file-processor :refer :all]
            [org.theparanoidtimes.filer.html.html-processor :refer :all]
            [org.theparanoidtimes.filer.html.html-assertion-helpers :refer :all]
            [org.theparanoidtimes.filer.html.html-utils :refer :all]
            [org.theparanoidtimes.filer.xml.xml-processor :refer :all]
            [org.theparanoidtimes.filer.xml.xml-utils :refer :all]))

(defonce test-dir (io/file "test/resources"))
(defonce test-html (io/file "test/resources/test.html"))
(defonce test-txt (io/file "test/resources/test.txt"))
(defonce test-xml (io/file "test/resources/test.xml"))
(defonce test-xml-schema (io/file "test/resources/test-schema.xsd"))
