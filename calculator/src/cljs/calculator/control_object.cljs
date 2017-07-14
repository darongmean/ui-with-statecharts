(ns calculator.control-object
  (:require [calculator.statemachine.switch-statement :as switch-statement]))


(defn init-state [*app-state]
  (reset! *app-state switch-statement/init-state))


(defn dispatch [*state event]
  (swap! *state switch-statement/transition-state event))
