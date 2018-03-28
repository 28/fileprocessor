(ns org.theparanoidtimes.filer.test.util-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.util :refer :all])
  (:import [java.io File]))

(deftest test-file-type-extraction
  (testing "plain-file-type-extraction"
    (is (= ".test"
           (file-type (File. "test.test")))))
  (testing "nil-file-type-extraction"
    (is (= nil
           (file-type (File. "test")))))
  (testing "multiple-dots-in-file-name-type-extraction"
    (is (= ".test"
           (file-type (File. "first.second.third.test"))))))
