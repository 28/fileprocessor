(defproject fileprocessor "0.1.0"
  :url "www.github.com/28/fileprocessor"
  :description "The idea of the fileprocessor project is to do batch processing
                of various files and file types."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljfmt "0.3.0"]]
  :profiles {:dev
             {:source-paths ["dev"]}}
  :repl-options {:prompt (fn [ns] (str "[" ns "] $ "))
                 :init-ns dev}
  :aot [com.paranoidtimes.fileprocessor.html.html-assertion-helpers]
  :uberjar-name "fileprocessor-standalone-0.1.0.jar"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [enlive "1.1.5"]
                 [org.clojure/data.xml "0.0.8"]])
