(ns org.theparanoidtimes.filer.test.xml-test
  (:require [org.theparanoidtimes.filer.xml.core :refer :all]
            [clojure.test :refer :all]
            [clojure.java.io :as io]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]))

(deftest xml-schema-test
  (testing "conforming schema and xml should pass the check"
    (is (validate-xml-against-xsd (io/file (tu/r "test.xml"))
                                  (io/file (tu/r "test-schema.xsd"))))))
