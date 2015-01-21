(ns leiningen.new.repl
  (:require [leiningen.new.common :refer :all]))

(defn figwheel?
  [options]
  (if (some #{"+figwheel"} (:features options))
    (-> options
        (append-options :dev-dependencies [['figwheel "0.1.7-SNAPSHOT"]])
        (append-options :dev-plugins [['lein-figwheel "0.1.7-SNAPSHOT"]])
        (append-options :core-dependencies [['figwheel.client :as 'fw]])
        (assoc :figwheel? true))
    options))

(defn weasel?
  [options]
  (if (some #{"+weasel"} (:features options))
    (-> options
        (append-options :dev-dependencies [['com.cemerick/piggieback "0.1.3"]
                                           ['weasel "0.4.2"]])
        (append-options :core-dependencies [['weasel.repl :as 'ws-repl]])
        (update-in [:repl-options]
                   assoc :nrepl-middleware ['cemerick.piggieback/wrap-cljs-repl])
        (assoc :weasel? true))
    options))

(defn repl-features [[assets options :as state]]
  [assets (-> options
              figwheel?
              weasel?)])
