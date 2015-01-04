(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write a game description"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2511"]
                 [figwheel "0.1.7"]
                 [retro-fever "0.1.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.1.7"]]
  :cljsbuild {
    :builds [{
      :source-paths ["src"]
        :compiler {
          :output-to "resources/public/game/{{name}}.js"
          :output-dir "resources/public/game/out"
          :optimizations :none
          :pretty-print true}}]})
