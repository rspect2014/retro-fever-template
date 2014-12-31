(ns leiningen.new.retro-fever
  (:require [leiningen.new.templates :refer [renderer sanitize year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "retro-fever"))

(defn retro-fever
  "Create a basic retro-fever project"
  [name]
  (let [data {:name name
              :sanitized (sanitize name)
              :year (year)}]
    (main/info "Generating fresh retro-fever project.")
    (->files data
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["resources/index.html" (render "index.html" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             [".gitignore" (render "gitignore" data)])))
