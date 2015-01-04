(ns {{name}}.core
  (:require-macros [retro-fever.macros :refer [game]])
  (:require [weasel.repl :as ws-repl]
            [retro-fever.core :as core]
            [retro-fever.input :as input]
            [retro-fever.sprite :as sprite]
            [retro-fever.asset :as asset]))

(enable-console-print!)

(defonce game-state (atom {})) ; Atom to store the game state

(defn update-fn []) ; Update function called on each iteration in the game loop

(defn render-fn [context]) ; Render function called on each iteration in the game loop

(defn load-resources []) ; Function to load all the game resources

(defn setup []) ; Function to setup initial game state

(if-not (ws-repl/alive?)
  (do (print "Open REPL")
      (ws-repl/connect "ws://localhost:9001"
                       :verbose true
                       :print #{:repl :console}
                       :on-error #(print "Error! " %))))

(defn ^:export init [] ; The entry point into the game from the HTML page
  (.log js/console "Launching game")

  (core/init-canvas "game-canvas" 640 480) ; Initialize canvas on HTML page
  (input/init) ; Initialize input devices

  (load-resources) ; Load game resources, such as images and animations
  (core/setup setup) ; Setup the initial game state
  (game core/game-loop update-fn render-fn 60) ; Create and start the game loop
)
