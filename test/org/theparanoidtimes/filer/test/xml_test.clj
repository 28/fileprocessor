(ns org.theparanoidtimes.filer.test.xml-test
  (:require [org.theparanoidtimes.filer.xml.core :refer :all]
            [clojure.test :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]))

(deftest xml-schema-test
  (testing "conforming schema and xml should pass the check"
    (is (validate-xml-against-xsd (tu/r "test.xml")
                                  (tu/r "test-schema.xsd"))))
  (testing "not valid xml should not pass the check"
    (is (false? (validate-xml-against-xsd (tu/r "test.html")
                                          (tu/r "test-schema.xsd"))))))
