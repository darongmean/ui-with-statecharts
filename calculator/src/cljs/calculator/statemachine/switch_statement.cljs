(ns calculator.statemachine.switch-statement
  (:require [calculator.statemachine.state-tag :as state-tag]
            [calculator.statemachine.state :as state]
            [calculator.statemachine.event :as event]))


(defn transition-state [{:keys [state] :as app-state} {:keys [signal] :as event}]
  (condp = state
    state-tag/start
    (condp = signal
      event/num-sig (state/operand1 app-state event)
      (state/ignore app-state event))

    state-tag/operand1
    (condp = signal
      event/operator-sig (state/operator-entered app-state event)
      (state/ignore app-state event))

    state-tag/operator-entered
    (condp = signal
      event/num-sig (state/operand2 app-state event)
      (state/ignore app-state event))

    state-tag/operand2
    (condp = signal
      event/equal-sig (state/start app-state)
      (state/ignore app-state event))))
