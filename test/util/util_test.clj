(ns util.util-test
  (:require [clojure.test :refer :all]
            [com.paranoidtimes.fileprocessor.utils :refer :all])
  (:import [java.io File]))

(deftest test-file-type-extraction
  (testing "file-type testing"
  (is (= ".test"
         (file-type (File. "test.test"))))))

