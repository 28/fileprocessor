(ns raw-file-processor-test
  (:require [clojure.test :refer :all] 
            [com.paranoidtimes.fileprocessor.raw-file-processor :refer :all]))

(deftest raw-file-processor-test
  (testing "raw file processor will change contents of the file"
    (is (= 1 (replace-text-in-files "./test/resources" "LOREM IPSUM" "lorem ipsum" #{".txt"})))
    (is (= 1 (replace-text-in-files "./test/resources" "lorem ipsum" "LOREM IPSUM" #{".txt"}))))
  (testing "raw file processor will not attempt to change the contents if old and new patter are the same"
    (is (= 0 (replace-text-in-files "./test/resources" "LOREM IPSUM" "LOREM IPSUM" #{".txt"})))))
