(ns org.theparanoidtimes.filer.directory-processor-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.directory-processor :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]
            [clojure.java.io :as io]))

(use-fixtures :once tu/with-test-folder)

(deftest directory-processor-testing
  (testing "files-in-directory returns all files in dir"
    (is (tu/file-seq-contains-by-name "test.html" (files-in-directory tu/test-resources-dir)))
    (is (tu/file-seq-contains-by-name "test.txt" (files-in-directory tu/test-resources-dir)))
    (is (= 3 (count (files-in-directory tu/test-resources-dir)))))
  (testing "files-in-directory returns empty list when there are no files of the specified file type"
    (is (empty? (files-in-directory tu/test-resources-dir #{".clj"}))))
  (testing "files-in-directory return a list of files of the specified file type"
    (is (tu/file-seq-contains-by-name "test.txt" (files-in-directory tu/test-resources-dir #{".txt"})))))

(deftest directory-generation-test
  (testing "n directories will be generated"
    (generate-n-directories (tu/f "f") 3)
    (is (tu/file-seq-contains-by-name "f0" (file-seq (io/file tu/test-dir))))
    (is (tu/file-seq-contains-by-name "f1" (file-seq (io/file tu/test-dir))))
    (is (tu/file-seq-contains-by-name "f2" (file-seq (io/file tu/test-dir))))))
