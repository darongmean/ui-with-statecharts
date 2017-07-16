(ns calculator.statemachine.state
  (:require [calculator.statemachine.state-tag :as state-tag]))


(def init
  {:state    state-tag/start
   :num1     0
   :num2     0
   :operator nil
   :result   0})


(defn ignore [app-state event]
  (do (println "no match signal: " event)
      app-state))


(defn operand1 [app-state {:keys [number]}]
  (-> app-state
    (assoc :state state-tag/operand1)
    (assoc :num1 number)))


(defn operator-entered [app-state {:keys [operator]}]
  (-> app-state
    (assoc :state state-tag/operator-entered)
    (assoc :operator operator)))


(defn operand2 [app-state {:keys [number]}]
  (-> app-state
    (assoc :state state-tag/operand2)
    (assoc :num2 number)))


(defn start [{:keys [operator num1 num2] :as app-state}]
  (-> app-state
    (assoc :state state-tag/start)
    (assoc :num1 0)
    (assoc :num2 0)
    (assoc :result (operator num1 num2))))
