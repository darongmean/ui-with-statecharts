(ns calculator.components.ui
  (:require [com.stuartsierra.component :as component]
            [calculator.user-interface-object :refer [render]]))

(defrecord UIComponent [control-object-component]
  component/Lifecycle
  (start [component]
    (render (:app-state-atom control-object-component))
    component)
  (stop [component]
    component))

(defn new-ui-component []
  (map->UIComponent {}))
