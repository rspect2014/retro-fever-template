(ns leiningen.new.common
  (:require
   [selmer.parser :as selmer]
   [leiningen.new.templates :refer [renderer ->files]]
   [clojure.pprint :refer [code-dispatch pprint with-pprint-dispatch]]))

(def dependency-indent 17)
(def dev-dependency-indent 24)
(def dev-plugin-indent 24)
(def plugin-indent 12)
(def root-indent 2)
(def dev-indent 9)
(def nrepl-options-indent 16)
(def ns-require-indent 12)

(defn render-template [template options]
  (selmer/render
   (str "<% safe %>" template "<% endsafe %>")
   options
   {:tag-open \< :tag-close \> :filter-open \< :filter-close \>}))

(defn init-render [] (renderer "retro-fever" render-template))

(defn slurp-resource [path]
  (-> (str "leiningen/new/retro_fever/" path)
      clojure.java.io/resource
      slurp))

(defn render-asset [render options asset]
  (if (string? asset)
    asset
    (let [[target source] asset]
      [target (render source options)])))

(defn render-assets [render assets options]
  (apply ->files options (map (partial render-asset render options) assets)))

(defn pprint-code [code]
  (-> (pprint code)
      with-out-str
      (.replaceAll "," "")))

(defn form->str [form]
  (let [text (pprint-code form)]
    (subs text 0 (count text))))

(defn indented-code [n form]
  (let [text (form->str form)
        indents (apply str (repeat n " "))]
    (.replaceAll (str text) "\n" (str "\n" indents))))

(defn indent [n form]
  (let [indents (apply str (repeat n " "))]
    (if (map? form)
      (-> (indented-code n form)
          clojure.string/trim
          clojure.string/trim-newline)
      (->> form
           (map str)
           (clojure.string/join (str "\n" indents))))))

(defn unwrap-map [text]
  (let [sb (StringBuilder. text)]
    (.setCharAt sb (.indexOf text "{") \space)
    (.setCharAt sb (.lastIndexOf text "}") \space)
    (.toString sb)))

(defn append-options [options k v]
  (update-in options [k] (fnil into []) v))
