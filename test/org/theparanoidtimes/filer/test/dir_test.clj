(ns org.theparanoidtimes.filer.test.dir-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.dir.core :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]))

(use-fixtures :once tu/with-test-folder)

(deftest directory-querying-test
  (testing "files-in-directory returns all files in dir"
    (is (tu/file-seq-contains-files #{"test.html" "test.txt" "test.xml" "test-schema.xsd"} (files-in-directory tu/test-resources-dir-name))))
  (testing "files-in-directory returns empty list when there are no files of the specified file type"
    (is (empty? (files-in-directory tu/test-resources-dir-name #{".clj"}))))
  (testing "files-in-directory return a list of files of the specified file type"
    (is (tu/file-seq-contains-file "test.txt" (files-in-directory tu/test-resources-dir-name #{".txt"}))))
  (testing "files-in-directory return a list of files of the specified file types"
    (is (tu/file-seq-contains-files #{"test.txt" "test-schema.xsd"} (files-in-directory tu/test-resources-dir-name #{".txt" ".xsd"})))))

(deftest directory-generation-test
  (testing "n directories will be generated"
    (generate-n-directories (tu/fname "f") 3)
    (is (tu/file-seq-contains-files #{"f0" "f1" "f2"} (file-seq (tu/test-dir))))))
