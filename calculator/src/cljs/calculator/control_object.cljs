(ns calculator.control-object
  (:require [calculator.statemachine.state :as state]
            [calculator.statemachine.switch-statement :as switch-statement]))


(defn init-state [*app-state]
  (reset! *app-state state/init))


(defn dispatch [*state event]
  (swap! *state switch-statement/transition-state event))
