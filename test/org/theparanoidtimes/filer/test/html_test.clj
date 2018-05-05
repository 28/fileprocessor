(ns org.theparanoidtimes.filer.test.html-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.html.core :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]))

(def f (tu/r "test.html"))

(deftest to-selector-vec-test
  (testing "successfully convert string to selector vec"
    (is (= [:body :div :p :a] (to-selector-vec "body div p a")))
    (is (= [:body :> :div :> :p :> :a] (to-selector-vec "body > div > p > a")))
    (is (= [:body.id :> :div#class :> :p :> :a] (to-selector-vec "body.id > div#class > p > a")))))

(deftest content-filter-test
  (testing "filter whitespace characters"
    (is (= ["a"] (filter-content ["a" "\n" "\t" "\r" " "])))
    (is (= ["a" "b"] (filter-content ["a" "\n    " "b"])))
    (is (= ["a"] (filter-content ["a" "\t    "])))
    (is (= ["a"] (filter-content ["a" "\r    "])))
    (is (= ["a"] (filter-content ["a" "      "]))))
  (testing "filter whitespace but preserve nested collections"
    (is (= ["a" '("b")] (filter-content ["a" "\n" '("b")])))
    (is (= ["a" {:a 1 :b "2"}] (filter-content ["a" "\n  " {:a 1 :b "2"}])))))

(deftest html-node-content-assertion-test
  (testing "node content is equal"
    (is (true? (assert-select f [:div#top :> :header :> :h1] (content=? "HTML5 Test Page"))))))

(deftest html-node-attributes-assertion-test
  (testing "attributes map contains key value pair"
    (is (true? (assert-select f [:fieldset#forms__radio :> :ul] (attributes-contain? :class "list list--bare"))))))

(deftest html-link-node-assertion
  (testing "link name assertion"
    (is (true? (assert-select f [:article#embedded__progress :> :footer :> :p :> :a] (link-name=? "[Top]")))))
  (testing "link href assertion"
    (is (true? (assert-select f [:article#embedded__progress :> :footer :> :p :> :a] (link-target=? "#top"))))))

(deftest html-img-node-assertion
  (testing "image src assertion"
    (is (true? (assert-select f [:article#embedded__images :> :div :> :p :> :img] (img-src=? "http://placekitten.com/480/480")))))
  (testing "image alt assertion"
    (is (true? (assert-select f [:article#embedded__images :> :div :> :p :> :img] (img-src-alt=? "http://placekitten.com/480/480" "Image alt text")))))
  (testing "image as link assertion"
    (is (true? (assert-select f [:p#img-as-link :> :a] (img-as-link=? "http://placekitten.com/480/480" "#top"))))))
