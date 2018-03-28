(ns org.theparanoidtimes.filer.test.raw-file-processor-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.raw-file-processor :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]
            [org.theparanoidtimes.filer.directory-processor :as dir]
            [clojure.java.io :as io]))

(use-fixtures :each tu/with-test-folder)

(deftest replace-text-in-file-test
  (testing "replace existing text in file"
    (spit (tu/f "r.txt") "TO_REPLACE")
    (replace-text-in-file (io/file (tu/f "r.txt")) #"TO_REPLACE" "REPLACED")
    (is (= "REPLACED" (slurp (io/file (tu/f "r.txt")))))))

(deftest replace-whole-text-in-file-test
  (testing "replace whole text in file"
    (spit (tu/f "r.txt") "XXXXXXXXX")
    (replace-whole-text-in-file (io/file (tu/f "r.txt")) "REPLACED")
    (is (= "REPLACED" (slurp (io/file (tu/f "r.txt")))))))

(deftest genreate-files-test
  (testing "generating-files"
    (generate-n-files (tu/f "f") 3 ".e" "dummy")
    (is (= 3 (count (dir/files-in-directory tu/test-dir))))
    (is (some #{"f0.e" "f1.e" "f2.e"} (map #(.getName %) (dir/files-in-directory tu/test-dir))))))

(deftest generate-files-from-names-list-test
  (testing "generate-files-from-names-list"
    (spit (tu/f "nl.txt") "_test/a1.txt\n_test/a2.txt\n_test/a3.txt")
    (generate-files-from-names-list (tu/f "nl.txt") identity "dummy")
    (is (= 4 (count (dir/files-in-directory tu/test-dir))))
    (is (some #{"a1.txt" "a2.txt" "a3.txt" "nl.txt"} (map #(.getName %) (dir/files-in-directory tu/test-dir))))))
