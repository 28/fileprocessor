(ns org.theparanoidtimes.filer.html_utils_test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.html.html-utils :refer :all]))

(deftest test-to-enlive-selector
  (testing "function-returns-enlive-selector-for-one-tag"
    (is (= [:div]
           (to-enlive-selector "div"))))
  (testing "function-returns-enlive-selector-for-multiple-tags"
    (is (= [:div :div :p]
           (to-enlive-selector "div div p")))))
