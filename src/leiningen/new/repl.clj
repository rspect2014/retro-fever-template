(ns leiningen.new.repl
  (:require [leiningen.new.common :refer :all]))

(defn figwheel?
  [options]
  (if (some #{"+figwheel"} (:features options))
    (-> options
        (append-options :dev-plugins [['lein-figwheel "0.5.8"]])
        (append-options :dev-requires [['figwheel.client :as 'fw]])
        (assoc :figwheel? true))
    options))

(defn weasel?
  [options]
  (if (some #{"+weasel"} (:features options))
    (-> options
        (append-options :dev-dependencies [['com.cemerick/piggieback "0.2.1"]
                                           ['weasel "0.7.0" :exclusions ['org.clojure/clojurescript]]])
        (append-options :dev-requires [['weasel.repl :as 'ws-repl]])
        (update-in [:repl-options]
                   assoc :nrepl-middleware ['cemerick.piggieback/wrap-cljs-repl])
        (assoc :weasel? true))
    options))

(defn repl-features [[assets options :as state]]
  [assets (-> options
              figwheel?
              weasel?)])
