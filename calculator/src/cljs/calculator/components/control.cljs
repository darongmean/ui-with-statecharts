(ns calculator.components.control
  (:require [com.stuartsierra.component :as component]
            [calculator.control-object :as control-object]))


(defrecord ControlObjectComponent [app-state-atom]
  component/Lifecycle
  (start [this]
    (control-object/init-state app-state-atom)
    this)
  (stop [this]
    (assoc this :app-state-atom (atom nil))))


(defn new-control-object-component []
  (map->ControlObjectComponent {:app-state-atom (atom nil)}))

