(ns leiningen.new.retro-fever
  (:require [leiningen.new.templates :refer [renderer sanitize year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "retro-fever"))

(defn retro-fever
  "Create a basic retro-fever project"
  ([name]
   (retro-fever name nil))
  ([name opt]
   (let [data {:name name
               :sanitized (sanitize name)
               :year (year)}
         prefix (when-let [prefix-name (some #{"figwheel"} [opt])]
                  (str prefix-name "-"))]
     (main/info "Generating fresh retro-fever project.")
     (->files data
              ["src/{{sanitized}}/core.cljs" (render (str prefix "core.cljs") data)]
              ["resources/public/index.html" (render "index.html" data)]
              ["project.clj" (render (str prefix "project.clj") data)]
              ["README.md" (render "README.md" data)]
              ["LICENSE" (render "LICENSE" data)]
              [".gitignore" (render "gitignore" data)]))))
