(ns leiningen.new.engine
  (:require [leiningen.new.common :refer :all]))

(defn engine-features [[assets options :as state]]
  (if (some #{"+scene"} (:features options))
    [assets
     (-> options
         (append-options :core-requires [['retro-fever.scene :as 'scene]])
         (assoc :scene? true))]
    state))
