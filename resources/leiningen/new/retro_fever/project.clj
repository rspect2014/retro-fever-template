(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/jessitron/{{name}}"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [retro-fever "0.1.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {
    :builds [{
      :source-paths ["src"]
        :compiler {
          :output-to "resources/public/js/{{name}}.js"
          :optimizations :whitespace
          :pretty-print true}}]})
