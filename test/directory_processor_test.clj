(ns directory-processor-test
  (:require [clojure.test :refer :all]
            [com.paranoidtimes.fileprocessor.directory-processor :refer :all]))

(defonce test-dir "./test/resources")

(deftest directory-processor-testing
  (testing "files-in-directory returns all files in dir"
    (is (some #(.contains (.getName %) "test.html") (files-in-directory test-dir)))
    (is (some #(.contains (.getName %) "test.txt") (files-in-directory test-dir)))
    (is (= 3 (count (files-in-directory test-dir)))))
  (testing "files-in-directory returns empty list when there are no files of the specified file type"
    (is (empty? (files-in-directory test-dir ".clj"))))
  (testing "files-in-directory return a list of files of the specified file type"
    (is (some #(.contains (.getName %) "test.txt") (files-in-directory test-dir ".txt")))))
