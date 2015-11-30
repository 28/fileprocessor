(ns dev
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer (pprint)]
            [clojure.java.io :refer :all]
            [clojure.string :as s]
            [com.paranoidtimes.fileprocessor.directory-processor :refer :all]
            [com.paranoidtimes.fileprocessor.utils :refer :all]
            [com.paranoidtimes.fileprocessor.raw-file-processor :refer :all]
            [com.paranoidtimes.fileprocessor.html.html-processor :refer :all]
            [com.paranoidtimes.fileprocessor.html.html-assertion-helpers :refer :all]
            [com.paranoidtimes.fileprocessor.html.html-utils :refer :all]))

(defonce test-dir (file "test/resources"))
(defonce test-html (file "test/resources/test.html"))
(defonce test-txt (file "test/resources/test.txt"))
