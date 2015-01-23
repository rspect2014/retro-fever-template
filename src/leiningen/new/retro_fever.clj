(ns leiningen.new.retro-fever
  (:require [leiningen.new.templates :refer [name-to-path year
                                             sanitize sanitize-ns project-name]]
            [leinjacker.utils :refer [lein-generation]]
            [selmer.parser :as selmer]
            [leiningen.core.main :as main]
            [leiningen.new.common :refer :all]
            [leiningen.new.engine :refer [engine-features]]
            [leiningen.new.repl :refer [repl-features]])
  (:import java.io.File))

(def core-assets
  [[".gitignore" "core/gitignore"]
   ["project.clj" "core/project.clj"]
   ["README.md" "core/README.md"]
   ["LICENSE" "core/LICENSE"]

   ;; core namespaces
   ["src/<<sanitized>>/core.cljs" "core/src/core.cljs"]

   ;; resources
   ["resources/public/index.html" "core/resources/index.html"]])

(defn render-template [template options]
  (selmer/render
   (str "<% safe %>" template "<% endsafe %>")
   options
   {:tag-open \< :tag-close \> :filter-open \< :filter-close \>}))

(defn format-options [options]
  (-> options
      (update-in [:dependencies] (partial indent dependency-indent))
      (update-in [:dev-dependencies] (partial indent dev-dependency-indent))
      (update-in [:plugins] (partial indent plugin-indent))
      (update-in [:dev-plugins] (partial indent dev-plugin-indent))
      (update-in [:core-requires] (partial indent ns-require-indent))
      (update-in [:dev-requires] (partial indent ns-require-indent))
      (update-in [:repl-options] (partial indent nrepl-options-indent))))

(defn generate-project
  "Create a new retro-fever project"
  [options]
  (main/info "Generating fresh retro-fever project.")
  (with-redefs [leiningen.new.templates/render-text render-template]
    (let [[assets options]
         (-> [core-assets options]
              engine-features
              repl-features)]
        (render-assets assets (format-options options)))))

(defn format-features
  [features]
  (apply str (interpose ", " features)))

(defn retro-fever
  "Creates a retro-fever project"
  [name & feature-params]
   (let [supported-features #{"+scene" "+weasel" "+figwheel"}
         options {:name (project-name name)
                  :min-lein-version "2.0.0"
                  :project-ns (sanitize-ns name)
                  :sanitized (name-to-path name)
                  :year (year)
                  :features (set feature-params)
                  :repl-options {:init-ns (symbol (str name ".core"))}}
         unsupported (-> (set feature-params)
                         (clojure.set/difference supported-features)
                         (not-empty))]
     (cond
      (< (lein-generation) 2)
      (main/info "Leiningen version 2.x is required.")

      (re-matches #"\A\+.+" name)
      (main/info "Project name is missing.\nTry: lein new retro-fever PROJECT_NAME"
                name (clojure.string/join " " (:features options)))

      unsupported
      (main/info "Unrecognized options:" (format-features unsupported)
                "\nSupported options are:" (format-features supported-features))

      (.exists (File. name))
      (main/info "Could not create project because a directory named" name "already exists!")

      :else
      (generate-project options))))
