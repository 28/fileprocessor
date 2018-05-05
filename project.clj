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
  :repl-options {:prompt  (fn [ns] (str "[" ns "] τπτ=> "))
                 :welcome (do (println "Evaluate (dev) to load the development namespace which has all namespaces loaded.")
                              (println "There are vars that can be used for dev/testing: test-dir, test-html, test-txt, test-xml and test-xsd."))
                 :init-ns user}
  :aot [org.theparanoidtimes.filer.html.assertion]
  :uberjar-name "filer-standalone-0.1.0.jar"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [enlive "1.1.6"]
                 [org.clojure/data.xml "0.0.8"]])
