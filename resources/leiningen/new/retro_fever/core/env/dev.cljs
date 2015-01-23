(ns <<name>>.game
  (:require [<<name>>.core :as core]<% if dev-requires %>
            <<dev-requires>><% endif %>))

(enable-console-print!)

<% if figwheel? %>(fw/start {:on-jsload (fn [] (print "reloaded"))})

<% endif %><% if weasel? %>(if-not (ws-repl/alive?)
  (do (print "Open REPL")
      (ws-repl/connect "ws://localhost:9001"
                       :verbose true
                       :print #{:repl :console}
                       :on-error #(print "Error! " %))))

<% endif %>(core/init!)
