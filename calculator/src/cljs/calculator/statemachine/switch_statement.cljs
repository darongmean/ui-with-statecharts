(ns calculator.statemachine.switch-statement
  (:require [calculator.statemachine.state :as state]
            [calculator.statemachine.event :as event]))


(defn ignore [app-state event]
  (do (println "no match signal: " event)
      app-state))


(defn operand1-state [app-state {:keys [number]}]
  (-> app-state
    (assoc :state state/operand1-state-tag)
    (assoc :num1 number)))


(defn operator-entered-state [app-state {:keys [operator]}]
  (-> app-state
    (assoc :state state/operator-entered-state-tag)
    (assoc :operator operator)))


(defn operand2-state [app-state {:keys [number]}]
  (-> app-state
    (assoc :state state/operand2-state-tag)
    (assoc :num2 number)))


(defn start-state [{:keys [operator num1 num2] :as app-state}]
  (-> app-state
    (assoc :state state/start-state-tag)
    (assoc :num1 0)
    (assoc :num2 0)
    (assoc :result (operator num1 num2))))


(def init-state
  {:state    state/start-state-tag
   :num1     0
   :num2     0
   :operator nil
   :result   0})


(defn transition-state [{:keys [state] :as app-state} {:keys [signal] :as event}]
  (condp = state
    state/start-state-tag
    (condp = signal
      event/num-sig (operand1-state app-state event)
      (ignore app-state event))

    state/operand1-state-tag
    (condp = signal
      event/operator-sig (operator-entered-state app-state event)
      (ignore app-state event))

    state/operator-entered-state-tag
    (condp = signal
      event/num-sig (operand2-state app-state event)
      (ignore app-state event))

    state/operand2-state-tag
    (condp = signal
      event/equal-sig (start-state app-state)
      (ignore app-state event))))
