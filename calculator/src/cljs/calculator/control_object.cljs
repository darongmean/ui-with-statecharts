(ns calculator.control-object
  (:require [calculator.statemachine.state :as state]
            [calculator.statemachine.switch-statement :as switch-statement]
            [calculator.statemachine.state-table :as state-table]
            [calculator.statemachine.state-design-pattern :as state-design-pattern]
            [calculator.statemachine.qep :as qep]))


(defn init-state [*app-state]
  (reset! *app-state qep/init-state))


(defn dispatch [*state event]
  (swap! *state qep/transition-state event))
