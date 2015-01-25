(defproject <<name>> "0.1.0-SNAPSHOT"
  :description "FIXME: write a game description"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2665"]
                 [retro-fever "0.2.0"]<% if dependencies %>
                 <<dependencies>><% endif %>]

  :min-lein-version "<<min-lein-version>>"

  :repl-options <<repl-options>>

  :plugins [[lein-cljsbuild "1.0.4"]<% if plugins %>
            <<plugins>><% endif %>]

  :cljsbuild {:builds
              {:game {:source-paths ["src"]
                      :compiler {:output-to "resources/public/game/<<sanitized>>.js"
                                 :output-dir "resources/public/game/out"
                                 :optimizations :none
                                 :pretty-print true}}}}

  :clean-targets ^{:protect false} ["resources/public/game/<<sanitized>>.js"
                                    "resources/public/game/out"]

  :profiles
  {:dev  {<% if any dev-dependencies dev-plugins%>:dependencies [<<dev-dependencies>>]
          :plugins [<<dev-plugins>>]
          <% endif %>:cljsbuild {:builds {:game {:source-paths ["env/dev"]}}}<%if all figwheel? weasel?%>
          :figwheel {:repl false}<%endif%>}
   :prod {:cljsbuild {:builds
                      {:game {:source-paths ["env/prod"]
                              :compiler {:optimizations :advanced}}}}}}
)
