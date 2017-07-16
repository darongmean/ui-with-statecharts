(ns calculator.control-object
  (:require [calculator.statemachine.state :as state]
            [calculator.statemachine.switch-statement :as switch-statement]
            [calculator.statemachine.state-table :as state-table]
            [calculator.statemachine.state-design-pattern :as state-design-pattern]))


(defn init-state [*app-state]
  (reset! *app-state state-design-pattern/init-state))


(defn dispatch [*state event]
  (swap! *state state-design-pattern/transition-state event))
