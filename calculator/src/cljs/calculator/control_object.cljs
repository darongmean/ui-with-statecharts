(ns calculator.control-object
  (:require [calculator.statemachine.state :as state]
            [calculator.statemachine.switch-statement :as switch-statement]
            [calculator.statemachine.state-table :as state-table]))


(defn init-state [*app-state]
  (reset! *app-state state/init))


(defn dispatch [*state event]
  (swap! *state state-table/transition-state event))
