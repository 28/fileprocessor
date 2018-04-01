(ns org.theparanoidtimes.filer.test.txt-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.txt.core :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]
            [org.theparanoidtimes.filer.dir.core :as dir]))

(use-fixtures :each tu/with-test-folder)

(deftest replace-text-in-file-test
  (testing "replace existing text in file"
    (spit (tu/fname "r.txt") "TO_REPLACE")
    (replace-text-in-file (tu/f "r.txt") #"TO_REPLACE" "REPLACED")
    (is (= "REPLACED" (slurp (tu/f "r.txt"))))))

(deftest replace-whole-text-in-file-test
  (testing "replace whole text in file"
    (spit (tu/fname "r.txt") "XXXXXXXXX")
    (replace-whole-text-in-file (tu/f "r.txt") "REPLACED")
    (is (= "REPLACED" (slurp (tu/f "r.txt"))))))

(deftest generate-files-test
  (testing "generating-files"
    (generate-n-files (tu/fname "f") 3 ".e" "dummy")
    (is (= 3 (count (dir/files-in-directory tu/test-dir-name))))
    (is (tu/file-seq-contains-files #{"f0.e" "f1.e" "f2.e"} (dir/files-in-directory tu/test-dir-name)))))

(deftest generate-files-from-names-list-test
  (testing "generate-files-from-names-list"
    (spit (tu/fname "nl.txt") "_test/a1.txt\n_test/a2.txt\n_test/a3.txt")
    (generate-files-from-names-list (tu/fname "nl.txt") identity "dummy")
    (is (= 4 (count (dir/files-in-directory tu/test-dir-name))))
    (is (tu/file-seq-contains-files #{"a1.txt" "a2.txt" "a3.txt" "nl.txt"} (dir/files-in-directory tu/test-dir-name)))))
