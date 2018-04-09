(ns org.theparanoidtimes.filer.html.core
  (:require [net.cgrand.enlive-html :as h]
            [clojure.string :as s])
  (:import (java.io StringReader)))


;; Util

(defmulti to-res
          "Converts the given resource to enlive html-resource (map of nodes)."
          (fn [obj] (type obj)))

(defmethod to-res String [s]
  "Converts the String resource to enlive html-resource."
  (h/html-resource (StringReader. s)))

(defmethod to-res :default [r]
  "Default to-res method."
  (h/html-resource r))

(defn to-selector-vec
  "Transforms the input to a enlive style selector - a vector of tag name
   keywords."
  [selector]
  (into [] (map keyword (s/split selector #"\s"))))


;; Assertion

(defn assert-select
  ([html node-path assertion-fn]
   (assert-select html node-path assertion-fn nil))
  ([html node-path assertion-fn opts]
   (->> html
        to-res
        (#(h/select % node-path))
        (#(if-let [order (:n opts)]
            (list (nth % order))
            %))
        (map assertion-fn)
        (every? true?))))

(defn content=?
  [c]
  (fn [node]
    (= (-> node :content first) c)))

(defn attributes-contain?
  [key value]
  (fn [node]
    (-> node
        (get :attrs)
        (get key)
        (= value))))

(defn link-name=?
  [name]
  (fn [node]
    (-> node
        (get :content)
        first
        (= name))))

(defn link-target=?
  [target]
  (fn [node]
    (-> node
        (get :attrs)
        (get :href)
        (= target))))

(defn img-src=?
  [src]
  (fn [node]
    (-> node
        (get :attrs)
        (get :src)
        (= src))))

(defn img-src-alt=?
  [src alt]
  (fn [node]
    (-> node
        (get :attrs)
        (#(and (= (get % :src) src)
               (= (get % :alt) alt))))))

(defn img-as-link=?
  [src href]
  (fn [node]
    (let [h (-> node
                (get :attrs)
                (get :href))
          s (-> node
                (get :content)
                (get :attrs)
                (get :src))]
      (and (= s src)
           (= h href)))))
