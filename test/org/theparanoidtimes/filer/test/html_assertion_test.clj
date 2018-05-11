(ns org.theparanoidtimes.filer.test.html-assertion-test
  (:require [clojure.test :refer :all]
            [org.theparanoidtimes.filer.html.assertion :refer :all]
            [org.theparanoidtimes.filer.test.test-utilities :as tu]))

(def f (tu/r "test.html"))

(deftest full-assertion-ns-test
  (testing "assert-node-content-is-equal"
    (is true? (assert-node-content-is-equal f [:div#top :> :header :> :h1] "HTML5 Test Page")))
  (testing "assert-nth-node-content-is-equal"
    (is true? (assert-nth-node-content-is-equal f [:h1] "HTML5 Test Page" 0)))
  (testing "assert-node-content-matches"
    (is true? (assert-node-content-matches f [:div#top :> :header :> :p] #"[a-zA-Z\s.]+")))
  (testing "assert-node-attribute-value"
    (is true? (assert-node-attribute-value f [:fieldset#forms__radio :> :ul] :class "list list--bare")))
  (testing "assert-link-name-is-equal"
    (is true? (assert-link-name-is-equal f [:article#embedded__progress :> :footer :> :p :> :a] "[Top]")))
  (testing "assert-link-target-is-equal"
    (is true? (assert-link-target-is-equal f [:article#embedded__progress :> :footer :> :p :> :a] "#top")))
  (testing "assert-img-src-is-equal"
    (is true? (assert-img-src-is-equal f [:article#embedded__images :> :div :> :p :> :img] "http://placekitten.com/480/480")))
  (testing "assert-img-src-and-alt-are-equal"
    (is true? (assert-img-src-and-alt-are-equal f [:article#embedded__images :> :div :> :p :> :img] "http://placekitten.com/480/480", "Image alt text")))
  (testing "assert-img-as-link-is-equal"
    (is true? (assert-img-as-link-is-equal f [:p#img-as-link :> :a] "http://placekitten.com/480/480" "#top"))))
