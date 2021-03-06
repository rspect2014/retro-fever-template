(ns <<name>>.core
  (:require-macros [retro-fever.macros :refer [game]])
  (:require [retro-fever.core :as core]
            [retro-fever.input :as input]
            [retro-fever.sprite :as sprite]
            [retro-fever.asset :as asset]
            <<core-requires>>))

(defonce game-state (atom {})) ; Atom to store the game state
<% if not scene? %>
(defn update-fn []) ; Update function called on each iteration in the game loop

(defn render-fn [context]) ; Render function called on each iteration in the game loop
<% endif %>
(defn load-resources []) ; Function to load all the game resources

(defn setup [] ; Function to setup initial game state
<% if scene? %>  (swap! game-state :scene (scene/scene))
<% endif %>)

(defn init! [] ; The entry point into the game from the HTML page
  (.log js/console "Launching game")

  (core/init-canvas "game-canvas" 640 480) ; Initialize canvas on HTML page
  (input/init) ; Initialize input devices

  (load-resources) ; Load game resources, such as images and animations
  (core/setup setup) ; Setup the initial game state

  <% if scene? %>(core/game game-state [:scene]) ; Start a game loop for the scene<% else %>(game core/game-loop update-fn render-fn 60) ; Create and start the game loop<% endif %>
)
