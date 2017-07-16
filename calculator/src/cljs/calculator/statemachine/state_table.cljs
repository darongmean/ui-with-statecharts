(ns calculator.statemachine.state-table
  (:require [calculator.statemachine.state-tag :as state-tag]
            [calculator.statemachine.state :as state]
            [calculator.statemachine.event :as event]))


(def table
  {[state-tag/start event/num-sig]            state/operand1
   [state-tag/operand1 event/operator-sig]    state/operator-entered
   [state-tag/operator-entered event/num-sig] state/operand2
   [state-tag/operand2 event/equal-sig]       (fn [s _] (state/start s))})


(defn transition-state [{:keys [state] :as app-state} {:keys [signal] :as event}]
  (let [transition-fn (table [state signal] state/ignore)]
    (transition-fn app-state event)))
