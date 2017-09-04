(ns utils-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.utils :refer :all])
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

(deftest test-first-n-function
  (testing "given-a-number-function-returns-that-much-elements"
    (is (= 2 (count (first-n 2 [1 2 3])))))
  (testing "given-a-nil-function-returns-all-elements"
    (is (= 3 (count (first-n 3 [1 2 3]))))))
