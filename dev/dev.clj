(ns dev
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer (pprint)]
            [clojure.java.io :refer :all]
            [clojure.string :as s]
            [com.paranoidtimes.filer.utils :refer :all]
            [com.paranoidtimes.filer.directory-processor :refer :all]
            [com.paranoidtimes.filer.raw-file-processor :refer :all]
            [com.paranoidtimes.filer.html.html-processor :refer :all]
            [com.paranoidtimes.filer.html.html-assertion-helpers :refer :all]
            [com.paranoidtimes.filer.html.html-utils :refer :all]
            [com.paranoidtimes.filer.xml.xml-processor :refer :all]
            [com.paranoidtimes.filer.xml.xml-utils :refer :all]))

(defonce test-dir (file "test/resources"))
(defonce test-html (file "test/resources/test.html"))
(defonce test-txt (file "test/resources/test.txt"))
(defonce test-xml (file "test/resources/test.xml"))
