(ns dev
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer (pprint)]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [org.theparanoidtimes.filer.util :refer :all]
            [org.theparanoidtimes.filer.dir.core :refer :all]
            [org.theparanoidtimes.filer.txt.core :refer :all]
            [org.theparanoidtimes.filer.xml.core :refer :all]
            [org.theparanoidtimes.filer.html.html-processor :refer :all]
            [org.theparanoidtimes.filer.html.html-assertion-helpers :refer :all]
            [org.theparanoidtimes.filer.html.html-utils :refer :all]))

(defonce test-dir (io/file "test/resources"))
(defonce test-html (io/file "test/resources/test.html"))
(defonce test-txt (io/file "test/resources/test.txt"))
(defonce test-xml (io/file "test/resources/test.xml"))
(defonce test-xml-schema (io/file "test/resources/test-schema.xsd"))
