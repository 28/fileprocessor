(defproject filer "0.1.0"
  :url "http://theparanoidtimes.org/projects/filer"
  :description "A library of various file manipulation function that strive to
                be highly reusable."
  :license {:name         "The MIT License"
            :distribution :repo
            :url          "https://opensource.org/licenses/MIT"}
  :min-lein-version "2.0.0"
  :profiles {:dev
             {:source-paths ["dev"]}}
  :repl-options {:prompt  (fn [ns] (str "[" ns "] $ "))
                 :init-ns dev}
  :aot [org.theparanoidtimes.filer.html.html-assertion-helpers]
  :uberjar-name "filer-standalone-0.1.0.jar"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [enlive "1.1.6"]
                 [org.clojure/data.xml "0.0.8"]])
