(ns calculator.system
  (:require [com.stuartsierra.component :as component]
            [calculator.components.ui :refer [new-ui-component]]
            [calculator.components.control :refer [new-control-object-component]]))

(declare system)

(defn new-system []
  (component/system-map
    :control-object-component (new-control-object-component)
    :app-root (component/using
                (new-ui-component)
                [:control-object-component])))

(defn init []
  (set! system (new-system)))

(defn start []
  (set! system (component/start system)))

(defn stop []
  (set! system (component/stop system)))

(defn ^:export go []
  (init)
  (start))

(defn reset []
  (stop)
  (go))
